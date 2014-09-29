import com.google.common.util.concurrent.ListenableFuture;
import com.impl.DefaultDependencyResolver;
import com.impl.LoggerImpl;
import com.interfaces.Credentials;
import com.interfaces.CredentialsFactory;
import com.interfaces.LogLevel;
import com.interfaces.Request;
import com.model.Message;
import com.model.User;
import com.odata.EntryPoint;

import java.util.List;


public class Program {
    public static void main(String[] args) {
        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6ImtyaU1QZG1Cdng2OHNrVDgtbVBBQjNCc2VlQSJ9.eyJhdWQiOiJodHRwczovL291dGxvb2sub2ZmaWNlMzY1LmNvbS8iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC81YWI5YWY5Yi00NTM0LTRjMzEtOGU1MC0xZTA5ODQ2MTQ4MWMvIiwiaWF0IjoxNDEyMDIxNjQ3LCJuYmYiOjE0MTIwMjE2NDcsImV4cCI6MTQxMjAyNTU0NywidmVyIjoiMS4wIiwidGlkIjoiNWFiOWFmOWItNDUzNC00YzMxLThlNTAtMWUwOTg0NjE0ODFjIiwiYW1yIjpbInB3ZCJdLCJvaWQiOiI5MTk5MzVjMy1lZGM4LTRhNDQtOWU3NC1mMGI4YzlmMWY0ODYiLCJ1cG4iOiJtYXJjb3N0QGxhZ2FzaC5jb20iLCJ1bmlxdWVfbmFtZSI6Im1hcmNvc3RAbGFnYXNoLmNvbSIsInN1YiI6Im5kbzRZYUZfVFN1TlE0LWlqeUk2UXNKYzNtUERoMzFwMUFweTh3dWw4Qm8iLCJwdWlkIjoiMTAwM0JGRkQ4M0NCQjExRSIsImZhbWlseV9uYW1lIjoidG9ycmVzIiwiZ2l2ZW5fbmFtZSI6Ik1hcmNvcyIsImFwcGlkIjoiNzc4YTA5OWUtZWQ2ZS00OWEyLTlmMTUtOTJjMDEzNjZhZDdkIiwiYXBwaWRhY3IiOiIwIiwic2NwIjoiQ2FsZW5kYXJzLlJlYWQgQ2FsZW5kYXJzLldyaXRlIENvbnRhY3RzLlJlYWQgQ29udGFjdHMuV3JpdGUgTWFpbC5SZWFkIE1haWwuU2VuZCBNYWlsLldyaXRlIHVzZXJfaW1wZXJzb25hdGlvbiIsImFjciI6IjEifQ.RBY5XtqWELwSg7lYr0V1dJTbSCxToUZtUpQjagr57kj3OE5NmBlB3-aRf_Sap9fOXSM9fPfwp0Bm_zmgMYCL3YvSPPw0i0aKpBnDHSQnQy13KJZy3cynYm4Ah4EEeQBaWEa8tSP2G1UYuEleZs3oE5LsdN6zsaTKzu4gd31j_HaYQGVETrGyzeMHY4FGbCm9qimyR-wbIxuSwNqwaTjGZQ2qPB9fhiMHDQKU6AFLgSTjTwXccAN8H1XhDFHd7SbvDKBfhacAjzxcOSlP1ex2AFjaajRRPm5ChRFgsbaLX4wzzfk70qL0wdeZEJFXfvRhr8ggq6BhWFxae7FZmYo3Ow";

        LoggerImpl logger = new LoggerImpl();
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
        } catch (Throwable t) {
            logger.log(t.toString(), LogLevel.VERBOSE);
        }

        ListenableFuture<User> future2 = entryPoint.getMe().execute();

        String alias;
        Message message;
        try {
            alias = future2.get().getAlias();
            logger.log(alias, LogLevel.VERBOSE);

            message = entryPoint.getMe().getInbox().getMessages().getById(id).execute().get();
            logger.log(message.getSubject(), LogLevel.VERBOSE);

        } catch (Throwable t) {
            logger.log(t.getMessage(), LogLevel.VERBOSE);
        }
    }
}
