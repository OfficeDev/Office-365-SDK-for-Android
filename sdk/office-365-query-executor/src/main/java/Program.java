import com.google.common.util.concurrent.ListenableFuture;
import com.impl.DefaultDependencyResolver;
import com.impl.LoggerImpl;
import com.impl.http.CredentialsImpl;
import com.interfaces.Credentials;
import com.interfaces.CredentialsFactory;
import com.interfaces.LogLevel;
import com.interfaces.Request;
import com.model.Message;
import com.model.User;
import com.odata.EntryPoint;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by marcote on 9/25/14.
 */
public class Program {
    public static void main(String[] args) {
        LoggerImpl logger = new LoggerImpl();

        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6ImtyaU1QZG1Cdng2OHNrVDgtbVBBQjNCc2VlQSJ9.eyJhdWQiOiJodHRwczovL291dGxvb2sub2ZmaWNlMzY1LmNvbS8iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC81YWI5YWY5Yi00NTM0LTRjMzEtOGU1MC0xZTA5ODQ2MTQ4MWMvIiwiaWF0IjoxNDEyMDEzODI3LCJuYmYiOjE0MTIwMTM4MjcsImV4cCI6MTQxMjAxNzcyNywidmVyIjoiMS4wIiwidGlkIjoiNWFiOWFmOWItNDUzNC00YzMxLThlNTAtMWUwOTg0NjE0ODFjIiwiYW1yIjpbInB3ZCJdLCJvaWQiOiI5MTk5MzVjMy1lZGM4LTRhNDQtOWU3NC1mMGI4YzlmMWY0ODYiLCJ1cG4iOiJtYXJjb3N0QGxhZ2FzaC5jb20iLCJ1bmlxdWVfbmFtZSI6Im1hcmNvc3RAbGFnYXNoLmNvbSIsInN1YiI6Im5kbzRZYUZfVFN1TlE0LWlqeUk2UXNKYzNtUERoMzFwMUFweTh3dWw4Qm8iLCJwdWlkIjoiMTAwM0JGRkQ4M0NCQjExRSIsImZhbWlseV9uYW1lIjoidG9ycmVzIiwiZ2l2ZW5fbmFtZSI6Ik1hcmNvcyIsImFwcGlkIjoiNzc4YTA5OWUtZWQ2ZS00OWEyLTlmMTUtOTJjMDEzNjZhZDdkIiwiYXBwaWRhY3IiOiIwIiwic2NwIjoiQ2FsZW5kYXJzLlJlYWQgQ2FsZW5kYXJzLldyaXRlIENvbnRhY3RzLlJlYWQgQ29udGFjdHMuV3JpdGUgTWFpbC5SZWFkIE1haWwuU2VuZCBNYWlsLldyaXRlIHVzZXJfaW1wZXJzb25hdGlvbiIsImFjciI6IjEifQ.De-ynAWO44l6F6vH4SHLqdFhO1s9G5w1_BOrqifEbMHeL47Y4QM9JetR2xS523x-gvabxariadXmKlWKSGq-1UPPvgAkz5ty7FmJbZNdypAO2rD8fkgvIOnpVBdmLgTBo3lTQroxESYFX7hd1-dIlXBNVJM-5_2u1glO3ePW6dXLI9-fobm58JPbpWVBLmAH4nGXz7W9a47dC3H0OUEsTwtru7Mv9D-IZqUa8QoEIBTV4zMwom5q-SpJ-IP7YcHudLA__77Fncj6zJrLYzvv5Voy9sid1Y1Sc_nMTzgI2tlr-Clq50VX6MB-kbOZrj6axrHzStbFIypmavX2V0aTrQ";

        DefaultDependencyResolver resolver = new DefaultDependencyResolver();

        resolver.setCredentialsFactory(new CredentialsFactory() {
            @Override
            public Credentials getCredentials() {
                return new Credentials() {
                    @Override
                    public void prepareRequest(Request request) {
                        request.addHeader("Authorization", "Bearer " + token);
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

        try {
            List<Message> messages = future.get();

            for (Message m : messages) {
                id = m.getId();
                logger.log(m.getSubject(), LogLevel.VERBOSE);
            }

            int count = messages.size();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ListenableFuture<User> future2 = entryPoint.getMe().execute();

        String alias = null;
        Message message = null;
        try {
            alias = future2.get().getAlias();
            logger.log(alias, LogLevel.VERBOSE);

            message = entryPoint.getMe().getInbox().getMessages().getById(id).execute().get();
            logger.log(message.getSubject(), LogLevel.VERBOSE);

        } catch (Throwable e) {
            e.printStackTrace();
        }

        // https://exchange.com/owa/me/messages?$top=10&skip=20
    }
}
