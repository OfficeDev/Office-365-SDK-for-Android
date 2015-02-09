/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.services.odata;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.services.odata.interfaces.HttpVerb;
import com.microsoft.services.odata.interfaces.ODataResponse;
import com.microsoft.services.odata.interfaces.ODataURL;
import com.microsoft.services.odata.interfaces.Request;

import java.io.IOException;
import java.io.InputStream;

import static com.microsoft.services.odata.Helpers.transformToVoidListenableFuture;


/**
 * The type ODataMediaEntityFetcher.
 *
 * @param <TEntity>     the type parameter
 * @param <TOperations> the type parameter
 */
public abstract class ODataMediaEntityFetcher<TEntity, TOperations extends ODataOperations>
        extends ODataEntityFetcher<TEntity, TOperations>
        implements Readable<TEntity> {

    /**
     * Instantiates a new ODataMediaEntityFetcher.
     *
     * @param urlComponent   the url component
     * @param parent         the parent
     * @param clazz          the clazz
     * @param operationClazz the operation clazz
     */

    public ODataMediaEntityFetcher(String urlComponent, ODataExecutable parent, Class<TEntity> clazz, Class<TOperations> operationClazz) {
        super(urlComponent, parent, clazz, operationClazz);
    }

    public ListenableFuture<byte[]> getContent() {

        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.GET);
        ODataURL url = request.getUrl();
        url.appendPathComponent("$value");

        ListenableFuture<ODataResponse> future = oDataExecute(request);

        return Futures.transform(future, new AsyncFunction<ODataResponse, byte[]>() {
            @Override
            public ListenableFuture<byte[]> apply(ODataResponse response) throws Exception {
                SettableFuture<byte[]> result = SettableFuture.create();
                result.set(response.getPayload());
                return result;
            }
        });
    }

    public ListenableFuture<InputStream> getStreamedContent() {

        Request request = getResolver().createRequest();
        request.setVerb(HttpVerb.GET);
        request.addOption(Request.MUST_STREAM_RESPONSE_CONTENT, "true");
        ODataURL url = request.getUrl();
        url.appendPathComponent("$value");

        ListenableFuture<ODataResponse> future = oDataExecute(request);

        return Futures.transform(future, new AsyncFunction<ODataResponse, InputStream>() {
            @Override
            public ListenableFuture<InputStream> apply(ODataResponse response) throws Exception {
                SettableFuture<InputStream> result = SettableFuture.create();
                result.set(new MediaEntityInputStream(response.openStreamedResponse(), response));
                return result;
            }
        });
    }


    public ListenableFuture<Void> putContent(byte[] content) {

        Request request = getResolver().createRequest();
        request.setContent(content);
        request.setVerb(HttpVerb.PUT);
        ODataURL url = request.getUrl();
        url.appendPathComponent("$value");

        ListenableFuture<ODataResponse> future = oDataExecute(request);

        return transformToVoidListenableFuture(future);
    }

    public ListenableFuture<Void> putContent(InputStream stream, long streamSize) {
        Request request = getResolver().createRequest();
        request.setStreamedContent(stream, streamSize);
        request.setVerb(HttpVerb.PUT);
        ODataURL url = request.getUrl();
        url.appendPathComponent("$value");

        ListenableFuture<ODataResponse> future = oDataExecute(request);

        return transformToVoidListenableFuture(future);
    }

    public class MediaEntityInputStream extends InputStream {
        private InputStream internalStream;
        private ODataResponse response;

        public MediaEntityInputStream(InputStream internalStream, ODataResponse response) {
            this.internalStream = internalStream;
            this.response = response;
        }

        @Override
        public int read() throws IOException {
            return internalStream.read();

        }

        @Override
        public void close() throws IOException {
            this.internalStream.close();
            this.response.closeStreamedResponse();
        }

        @Override
        public int available() throws IOException {
            return this.internalStream.available();
        }

        @Override
        public boolean markSupported() {
            return this.internalStream.markSupported();
        }

        @Override
        public synchronized void mark(int readlimit) {
            this.internalStream.mark(readlimit);
        }

        @Override
        public int read(byte[] b) throws IOException {
            return this.internalStream.read(b);
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            return this.internalStream.read(b, off, len);
        }

        @Override
        public synchronized void reset() throws IOException {
            this.internalStream.reset();
        }

        @Override
        public long skip(long n) throws IOException {
            return this.internalStream.skip(n);
        }
    }
}