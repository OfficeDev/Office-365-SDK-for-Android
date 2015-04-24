// ------------------------------------------------------------------------------
// Copyright (c) 2014 Microsoft Corporation
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//  THE SOFTWARE.
// ------------------------------------------------------------------------------

package com.microsoft.live;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.Log;

/**
 * The ScreenSize is used to determine the DeviceType.
 * Small and Normal ScreenSizes are Phones.
 * Large and XLarge are Tablets.
 */
enum ScreenSize {
    SMALL {
        @Override
        public DeviceType getDeviceType() {
            return DeviceType.PHONE;
        }
    },
    NORMAL {
        @Override
        public DeviceType getDeviceType() {
            return DeviceType.PHONE;
        }

    },
    LARGE {
        @Override
        public DeviceType getDeviceType() {
            return DeviceType.TABLET;
        }
    },
    XLARGE {
        @Override
        public DeviceType getDeviceType() {
            return DeviceType.TABLET;
        }
    };

    public abstract DeviceType getDeviceType();

    /**
     * Configuration.SCREENLAYOUT_SIZE_XLARGE was not provided in API level 9.
     * However, its value of 4 does show up.
     */
    private static final int SCREENLAYOUT_SIZE_XLARGE = 4;

    public static ScreenSize determineScreenSize(Activity activity)  {
        int screenLayout = activity.getResources().getConfiguration().screenLayout;
        int screenLayoutMasked = screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        switch (screenLayoutMasked) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return SMALL;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return NORMAL;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return LARGE;
            case SCREENLAYOUT_SIZE_XLARGE:
                return XLARGE;
            default:
                // If we cannot determine the ScreenSize, we'll guess and say it's normal.
                Log.d(
                    "Live SDK ScreenSize",
                    "Unable to determine ScreenSize. A Normal ScreenSize will be returned.");
                return NORMAL;
        }
    }
}
