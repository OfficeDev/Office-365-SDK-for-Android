package com.example.adal.test_generatedsdk_app;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.office365.odata.EntryPoint;
import com.microsoft.office365.odata.impl.DefaultDependencyResolver;
import com.microsoft.office365.odata.impl.LoggerImpl;
import com.microsoft.office365.odata.interfaces.Credentials;
import com.microsoft.office365.odata.interfaces.CredentialsFactory;
import com.microsoft.office365.odata.interfaces.LogLevel;
import com.microsoft.office365.odata.interfaces.Request;
import com.microsoft.office365.odata.model.Message;
import com.microsoft.office365.odata.model.User;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by marcote on 9/30/14.
 */
public class Tester {
    public static void doStuff() {

        final LoggerImpl logger = new LoggerImpl();

        try {
            //final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6ImtyaU1QZG1Cdng2OHNrVDgtbVBBQjNCc2VlQSJ9.eyJhdWQiOiJodHRwczovL291dGxvb2sub2ZmaWNlMzY1LmNvbS8iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC81YWI5YWY5Yi00NTM0LTRjMzEtOGU1MC0xZTA5ODQ2MTQ4MWMvIiwiaWF0IjoxNDEyMDI1MzIzLCJuYmYiOjE0MTIwMjUzMjMsImV4cCI6MTQxMjAyOTIyMywidmVyIjoiMS4wIiwidGlkIjoiNWFiOWFmOWItNDUzNC00YzMxLThlNTAtMWUwOTg0NjE0ODFjIiwiYW1yIjpbInB3ZCJdLCJvaWQiOiI5MTk5MzVjMy1lZGM4LTRhNDQtOWU3NC1mMGI4YzlmMWY0ODYiLCJ1cG4iOiJtYXJjb3N0QGxhZ2FzaC5jb20iLCJ1bmlxdWVfbmFtZSI6Im1hcmNvc3RAbGFnYXNoLmNvbSIsInN1YiI6Im5kbzRZYUZfVFN1TlE0LWlqeUk2UXNKYzNtUERoMzFwMUFweTh3dWw4Qm8iLCJwdWlkIjoiMTAwM0JGRkQ4M0NCQjExRSIsImZhbWlseV9uYW1lIjoidG9ycmVzIiwiZ2l2ZW5fbmFtZSI6Ik1hcmNvcyIsImFwcGlkIjoiNzc4YTA5OWUtZWQ2ZS00OWEyLTlmMTUtOTJjMDEzNjZhZDdkIiwiYXBwaWRhY3IiOiIwIiwic2NwIjoiQ2FsZW5kYXJzLlJlYWQgQ2FsZW5kYXJzLldyaXRlIENvbnRhY3RzLlJlYWQgQ29udGFjdHMuV3JpdGUgTWFpbC5SZWFkIE1haWwuU2VuZCBNYWlsLldyaXRlIHVzZXJfaW1wZXJzb25hdGlvbiIsImFjciI6IjEifQ.WRVHqcK0C9V8f2UqN8PmTpqZpo4E-nw5j2OPfQJa8mkIYOcUPBMF7p_ZmOaxIOO1D2W3JnwJl3MzuCWiTlysWtfUf-6Qg4_hLhRO0oyAJUm1BWJd1TIdFK6jpshuLi7aQpAob5A0KOyvLtEOy1G7_wLlK6JymriKDL8PyWirCnmV7zYARsE6DnSVZe_I9-kp7Rzuq_aP8FOq2QUpzIWFh02FABXr9NrHsCVJD0UY6ThXuHAGSIVZctiGulSwN2hOg1bQz-I7HYIbMTc5FPQlhzOW4LBNWzd62sxs4_QGoAoZnNbpwxf4ZSUG8xCzcMDsE5khGIlhijIBIhbVwas9Yg";

            DefaultDependencyResolver resolver = new DefaultDependencyResolver();

            resolver.setCredentialsFactory(new CredentialsFactory() {
                @Override
                public Credentials getCredentials() {
                    return new Credentials() {
                        @Override
                        public void prepareRequest(Request request) {
                            //request.addHeader("Authorization", "Bearer " + token);
                            request.addHeader("Authorization", "Basic bWFyY29zdEBsYWdhc2guY29tOmxhZ2FzaCEwMA==");
                        }
                    };
                }
            });

            String id = null;
            EntryPoint entryPoint = new EntryPoint("https://outlook.office365.com/EWS/OData", resolver);

            ListenableFuture<List<Message>> future = entryPoint.getMe().getInbox().getMessages()
                    .top(10)
                    .skip(20)
                    .execute();

            List<Message> messages = future.get();
            for (Message m : messages) {
                id = m.getId();
                logger.log(m.getSubject(), LogLevel.VERBOSE);
            }

            ListenableFuture<User> future2 = entryPoint.getMe().execute();

            String alias;
            Message message;

            alias = future2.get().getAlias();
            logger.log(alias, LogLevel.VERBOSE);

            message = entryPoint.getMe().getInbox().getMessages().getById(id).execute().get();
            logger.log(message.getSubject(), LogLevel.VERBOSE);


            //entryPoint.getMe().getInbox().getMessages().add(message).g


            Message m = new Message();
            m.setSubject("FromAndroid");
            ListenableFuture updateFuture = entryPoint
                    .getMe()
                    .getInbox()
                    .getMessages()
                    .getById("AAMkADg0N2IyYmY3LWY4ZDktNDczNy1iNjAwLTcwYjhhOWM0YjM0MgBGAAAAAABUiwVVm9hQT4oWiN47MJamBwAHg_6kMOm8T7tGK7OZBwRsAAAA999fAACVRFTLArY_Sqle5hmWIJigAAFdKYvTAAA=")
                    .update(m);

            Futures.addCallback(updateFuture, new FutureCallback<Message>() {
                @Override
                public void onSuccess(Message o) {
                    logger.log("Updated!", LogLevel.VERBOSE);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    logger.log("Error: " + throwable.toString(), LogLevel.VERBOSE);
                }
            });


        } catch (Throwable t) {
            StringWriter writer = new StringWriter();
            t.printStackTrace(new PrintWriter(writer));


            logger.log(writer.toString(), LogLevel.ERROR);
        }
    }
}
