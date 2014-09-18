/*******************************************************************************
 * Copyright © Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class BitmapResizer.
 */
public class BitmapResizer {

	/** The m metrics. */
	private DisplayMetrics mMetrics;
	
	/** The m size. */
	private int mSize;

	/**
	 * Instantiates a new bitmap resizer.
	 *
	 * @param metrics the metrics
	 */
	public BitmapResizer(DisplayMetrics metrics) {
		mMetrics = metrics;
		calculateSize();
	}

	/**
	 * Calculate size.
	 */
	private void calculateSize() {
		mSize = mMetrics.heightPixels < mMetrics.widthPixels ? mMetrics.heightPixels
				: mMetrics.widthPixels;
	}

	/**
	 * Read fully.
	 *
	 * @param input the input
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] readFully(InputStream input) throws IOException {
		byte[] buffer = new byte[8192];
		int bytesRead;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
		return output.toByteArray();
	}

	/**
	 * Gets the bitmap from.
	 *
	 * @param imageStream the image stream
	 * @return the bitmap from
	 */
	public Bitmap getBitmapFrom(InputStream imageStream) {
		Bitmap bitmap = null;
		try {
			byte[] bytes = readFully(imageStream);
			bitmap = getBitmapFrom(bytes);
			return bitmap;
		} catch (IOException e) {
			Log.e("Asset", e.getMessage());
		}
		return bitmap;
	}

	/**
	 * Gets the bitmap from a path
	 *
	 * @param path the path
	 * @return the bitmap from
	 */
	public Bitmap getBitmapFrom(String path) {
		Bitmap bitmap = null;
		try {

			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = mSize;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			bitmap = BitmapFactory.decodeFile(path, o2);
			return bitmap;

		} catch (Exception e) {
			Log.e("getBitmap", e.getMessage());
		}
		return bitmap;
	}

	/**
	 * Gets the bitmap from.
	 *
	 * @param data the data
	 * @return the bitmap from
	 */
	public Bitmap getBitmapFrom(byte[] data) {

		Bitmap bitmap = null;
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(data, 0, data.length, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = mSize;

			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, o2);
			return bitmap;

		} catch (Exception e) {
			Log.e("getBitmap", e.getMessage());
		}
		return bitmap;
	}

}
