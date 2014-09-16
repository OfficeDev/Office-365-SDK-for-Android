/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.assetmanagement.R;
import com.microsoft.office365.Credentials;
import com.microsoft.onedrivediscovery.adapters.DisplayFileItemAdapter;
import com.microsoft.onedrivediscovery.datasource.ListItemsDataSource;
import com.microsoft.onedrivediscovery.tasks.SaveFileTask;
import com.microsoft.onedrivediscovery.viewmodel.FileItem;
import com.microsoft.onedrivediscovery.viewmodel.ServiceViewItem;

// TODO: Auto-generated Javadoc
/**
 * The Class FileItemActivity.
 */
public class FileItemActivity extends FragmentActivity {

	/** The m car view item. */
	private FileItem mFileSaveItem;

	/** The m application. */
	private DiscoveryAPIApplication mApplication;
	private AppPreferences mPreferences;

	/** The Constant CAMARA_REQUEST_CODE. */
	final static int CAMARA_REQUEST_CODE = 1000;

	/** The Constant SELECT_PHOTO. */
	final static int SELECT_PHOTO = 1001;
	DisplayFileItemAdapter mAdapter;

	BitmapResizer mResizer;

	String mResourceId;
	String mEndPoint;

	/**
	 * Sets the car view item.
	 * 
	 * @param carListViewItem
	 *            the new file item
	 */
	public void setFileViewItem(FileItem fileSaveItem) {
		mFileSaveItem = fileSaveItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.file_view_menu, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		mApplication = (DiscoveryAPIApplication) getApplication();
		mPreferences = mApplication.getAppPreferences();

		setContentView(R.layout.activity_file_display);

		Bundle bundle = getIntent().getExtras();
		Uri imageUri = (Uri) bundle.get(Intent.EXTRA_STREAM);

		try {
			if (imageUri != null) {
				bundle = new ShareTask(this, imageUri, getIntent()).execute()
						.get().getExtras();
			}

			DisplayMetrics displayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			mResizer = new BitmapResizer(displayMetrics);

			String data = bundle.getString(Constants.DATA);
			if (data != null) {
				JSONObject payload;

				payload = new JSONObject(data);
				mFileSaveItem = new FileItem();
				mResourceId = payload.getString(Constants.RESOURCEID);
				mEndPoint = payload.getString(Constants.ENDPOINT);
				mFileSaveItem.setResourceId(mResourceId);
				mFileSaveItem.setEndpoint(mEndPoint);

				ShowImageToShare(Uri.parse(payload.getString("uri")));
			}
		} catch (Exception e) {
			Log.e("Asset", e.getMessage());
		}
	}

	private void ShowImageToShare(Uri uri) {

		try {

			InputStream imageStream = getContentResolver().openInputStream(uri);
			Bitmap bitmap = mResizer.getBitmapFrom(imageStream);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

			if (stream != null) {
				mFileSaveItem.setContent(stream.toByteArray());
				mAdapter = new DisplayFileItemAdapter(this,
						mFileSaveItem.getContent());
				ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
				viewPager.setAdapter(mAdapter);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home: {
			finish();
			return true;
		}
		case R.id.menu_save_image: {
			hideSoftPad();
			saveAction();
			return true;
		}
		case R.id.menu_file_save: {
			selectPicture();
			return true;
		}
		default:
			return true;
		}
	}

	/**
	 * Select picture.
	 */
	private void selectPicture() {
		final Activity that = this;

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				CharSequence[] sources = { "From Library", "From Camera" };
				AlertDialog.Builder builder = new AlertDialog.Builder(that);
				builder.setTitle("Select an option:").setSingleChoiceItems(
						sources, 0, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								dialog.dismiss();
								openPhotoSource(item);
							}

							private void openPhotoSource(int itemSelected) {
								switch (itemSelected) {
								case 0:
									invokePhotoLibrayIntent();
									break;
								case 1:
									invokeFromCameraIntent();
									break;
								default:
									break;
								}
							}

							private void invokeFromCameraIntent() {
								dispatchTakePictureIntent();
							}

							private void invokePhotoLibrayIntent() {
								Intent photoPickerIntent = new Intent(
										Intent.ACTION_PICK);
								photoPickerIntent.setType("image/*");
								startActivityForResult(photoPickerIntent,
										SELECT_PHOTO);
							}
						});
				builder.create().show();
			}
		});
	}

	/** The m current photo path. */
	String mCurrentPhotoPath;

	/**
	 * Creates the image file.
	 * 
	 * @return the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressLint("SimpleDateFormat")
	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents
		mCurrentPhotoPath = image.getAbsolutePath();
		return image;
	}

	/**
	 * Dispatch take picture intent.
	 */
	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createImageFile();
			} catch (IOException ex) {
				Log.e("Asset", ex.getMessage());
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, CAMARA_REQUEST_CODE);
			}
		}
	}

	/**
	 * Save action.
	 */
	private void saveAction() {
		hideSoftPad();

		mFileSaveItem.setName(((EditText) findViewById(R.id.textFileName))
				.getText().toString().trim());// .getText().toString();
		if (mFileSaveItem.getName().length() == 0
				|| mFileSaveItem.getContent() == null) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Information");
			builder.setMessage("All fields and a photo are required");
			builder.create().show();
			return;
		}

		new SaveFileTask(this).execute(mFileSaveItem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		final byte[] bytes = getImageData(requestCode, resultCode, data);

		if (bytes != null) {
			mFileSaveItem.setContent(bytes);
			mAdapter = new DisplayFileItemAdapter(this, bytes);

			ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
			viewPager.setAdapter(mAdapter);
		}
	}

	/**
	 * Gets the image data.
	 * 
	 * @param requestCode
	 *            the request code
	 * @param resultCode
	 *            the result code
	 * @param data
	 *            the data
	 * @return the image data
	 */
	private final byte[] getImageData(int requestCode, int resultCode,
			Intent data) {

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		switch (requestCode) {
		case SELECT_PHOTO: {
			if (resultCode == RESULT_OK) {

				try {
					Uri selectedImage = data.getData();

					InputStream imageStream = getContentResolver()
							.openInputStream(selectedImage);
					Bitmap bitmap = mResizer.getBitmapFrom(imageStream);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
					return stream.toByteArray();
				} catch (Throwable t) {
					mApplication.handleError(t);
				}
			}
		}
		case CAMARA_REQUEST_CODE: {
			if (resultCode == RESULT_OK) {
				try {
					if (mCurrentPhotoPath != null) {
						Bitmap bitmap = mResizer
								.getBitmapFrom(mCurrentPhotoPath);
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
						return stream.toByteArray();
					}
				} catch (Throwable t) {
					mApplication.handleError(t);
				}
			}
		}
		default:
			break;
		}
		return null;
	}

	/**
	 * Hide soft pad.
	 */
	private void hideSoftPad() {
		((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
				.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
	}

	protected void openFile(String fileName) {
		Intent install = new Intent(Intent.ACTION_VIEW);
		install.setDataAndType(Uri.fromFile(new File(fileName)), "MIME-TYPE");
		startActivity(install);
	}

	private class ShareTask extends AsyncTask<Void, Void, Intent> {

		Activity mActivity;
		Uri mUri;
		Intent mIntent;

		public ShareTask(Activity activity, Uri uri, Intent currentIntent) {
			mActivity = activity;
			mUri = uri;
			mIntent = currentIntent;
		}

		@Override
		protected Intent doInBackground(java.lang.Void... params) {

			ListenableFuture<Map<String, Credentials>> future = Authentication
					.authenticate(mActivity,
					Constants.DISCOVERY_RESOURCE_ID, mPreferences);

			Futures.addCallback(future,
					new FutureCallback<Map<String, Credentials>>() {
						@Override
						public void onFailure(Throwable t) {
							Log.e("Asset", t.getMessage());
						}

						@Override
						public void onSuccess(
								Map<String, Credentials> credentials) {
							setResourceAuthentication();
						}
					});

			return mIntent;
		}

		void setResourceAuthentication() {
			final ServiceViewItem item = new ListItemsDataSource(
					(DiscoveryAPIApplication) getApplication())
					.getFileService(); // startActivity(new
										// Intent(MainActivity.this,
										// ServiceListActivity.class));
			ListenableFuture<Map<String, Credentials>> future = Authentication
					.authenticate(mActivity, item.getResourceId(), mPreferences);

			Futures.addCallback(future,
					new FutureCallback<Map<String, Credentials>>() {
						@Override
						public void onFailure(Throwable t) {
							Log.e("Asset", t.getMessage());
						}

						@Override
						public void onSuccess(
								Map<String, Credentials> credentials) {

							JSONObject payload = new JSONObject();

							try {
								payload.put(Constants.RESOURCEID,
										item.getResourceId());
								payload.put(Constants.ENDPOINT,
										item.getEndpointUri());
								payload.put("uri", mUri);
								mIntent.putExtra(Constants.DATA,
										payload.toString());
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					});
		}
	}
}
