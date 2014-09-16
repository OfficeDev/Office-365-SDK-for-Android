/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information. 
 ******************************************************************************/
package com.microsoft.onedrivediscovery.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.microsoft.onedrivediscovery.BitmapResizer;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplayFileItemAdapter.
 */
public class DisplayFileItemAdapter extends PagerAdapter  {

	Activity mActivity;
	
	/** The m data. */
	private ArrayList<byte[]> mData;
	private BitmapResizer mResizer;
	
	public DisplayFileItemAdapter(Activity activity, byte[]  data){
		mActivity = activity;
		DisplayMetrics displayMetrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		mResizer = new BitmapResizer(displayMetrics);
		mData = new ArrayList<byte[]>(); 
		mData.add(data);
	}
	
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((ImageView) object);
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view.ViewGroup, int)
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView = new ImageView(mActivity);
		
		try {
			imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			imageView.setImageBitmap(mResizer.getBitmapFrom(mData.get(position)));
			((ViewPager) container).addView(imageView, 0);
		} catch (Exception e) {
			Log.e("Asset", e.getMessage());
		}
		return imageView;
	}
}
