/*******************************************************************************
 * Copyright (c) Microsoft Open Technologies, Inc.
 * All Rights Reserved
 * See License.txt in the project root for license information.
 ******************************************************************************/
package com.microsoft.office365.odata;

import com.google.common.util.concurrent.*;
import com.microsoft.office365.odata.interfaces.*;
import com.microsoft.office365.exchange.services.*;

public class MessageOperations extends ODataOperations {

	 public MessageOperations(String urlComponent, ODataExecutable parent) {
        super(urlComponent, parent);
    }
			
	public ListenableFuture<Message> copy(String destinationId) {
        final SettableFuture<Message> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Copy", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] message) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(message, Constants.UTF8), Message.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Message> move(String destinationId) {
        final SettableFuture<Message> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Move", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] message) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(message, Constants.UTF8), Message.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Message> createReply() {
        final SettableFuture<Message> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("CreateReply", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] message) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(message, Constants.UTF8), Message.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Message> createReplyAll() {
        final SettableFuture<Message> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("CreateReplyAll", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] message) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(message, Constants.UTF8), Message.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Message> createForward() {
        final SettableFuture<Message> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("CreateForward", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] message) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(message, Constants.UTF8), Message.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Integer> reply(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Reply", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] integer) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(integer, Constants.UTF8), Integer.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Integer> replyAll(String comment) {
        final SettableFuture<Integer> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("ReplyAll", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] integer) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(integer, Constants.UTF8), Integer.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Integer> forward(String comment, java.util.List<Recipient> toRecipients) {
        final SettableFuture<Integer> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Forward", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] integer) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(integer, Constants.UTF8), Integer.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
			
	public ListenableFuture<Integer> send() {
        final SettableFuture<Integer> result = SettableFuture.create();

        ListenableFuture<byte[]> future = oDataExecute("Send", null, HttpVerb.POST);

        Futures.addCallback(future, new FutureCallback<byte[]>() {
            @Override
            public void onSuccess(byte[] integer) {
                DependencyResolver resolver = getResolver();

                try {
                    result.set(resolver.getJsonSerializer().deserialize(new String(integer, Constants.UTF8), Integer.class));
                } catch (Throwable throwable) {
                    result.setException(throwable);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                result.setException(t);
            }
        });

        return result;
    }
}