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

import com.microsoft.live.QueryParameters;
import com.microsoft.live.UriBuilder;

/**
 * Enum that specifies what to do during a naming conflict during an upload.
 */
public enum OverwriteOption {

    /** Overwrite the existing file. */
    Overwrite {
        @Override
        protected String overwriteQueryParamValue() {
            return "true";
        }
    },

    /** Do Not Overwrite the existing file and cancel the upload. */
    DoNotOverwrite {
        @Override
        protected String overwriteQueryParamValue() {
            return "false";
        }
    },

    /** Rename the current file to avoid a name conflict. */
    Rename {
        @Override
        protected String overwriteQueryParamValue() {
            return "choosenewname";
        }
    };

    /**
     * Leaves any existing overwrite query parameter on appends this overwrite
     * to the given UriBuilder.
     */
    void appendQueryParameterOnTo(UriBuilder uri) {
        uri.appendQueryParameter(QueryParameters.OVERWRITE, this.overwriteQueryParamValue());
    }

    abstract protected String overwriteQueryParamValue();
}
