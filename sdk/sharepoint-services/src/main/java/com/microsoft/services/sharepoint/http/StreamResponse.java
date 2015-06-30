/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.sharepoint.http;

import com.microsoft.services.sharepoint.Constants;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Response implementation based on an InputStream
 */
public class StreamResponse implements Response {
	private BufferedReader mReader;
	private InputStream mStream;
	private int mStatus;
	Map<String, List<String>> mHeaders;

	/**
	 * Initializes the StreamResponse
	 * @param stream stream to read
	 * @param status HTTP status code
	 */
	public StreamResponse(InputStream stream, int status, Map<String, List<String>> headers) {
		mHeaders = new HashMap<String, List<String>>(headers);
		mReader = new BufferedReader(new InputStreamReader(stream, Constants.UTF8));
		mStream = stream;
		mStatus = status;
	}

	@Override
	public String readToEnd() throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = mReader.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
		}

		return sb.toString();
	}

	@Override
	public int getStatus() {
		return mStatus;
	}

	@Override
	public String readLine() throws IOException {
		return mReader.readLine();
	}

	@Override
	public Map<String, List<String>> getHeaders() {
		return new HashMap<String, List<String>>(mHeaders);
	}

	@Override
	public List<String> getHeader(String headerName) {
		return mHeaders.get(headerName);
	}

	@Override
	public byte[] readAllBytes() throws IOException {
		 ByteArrayOutputStream os = new ByteArrayOutputStream();
		 int nRead;
		 byte[] data = new byte[1024];

		 while ((nRead = mStream.read(data, 0, data.length)) != -1) {
		   os.write(data, 0, nRead);
		 }
		 return os.toByteArray();
	}
}
