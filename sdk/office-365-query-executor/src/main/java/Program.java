import com.google.common.util.concurrent.ListenableFuture;
import com.infrastructure.Credentials;
import com.infrastructure.DependencyResolver;
import com.model.Message;
import com.model.User;
import com.odata.EntryPoint;

import java.util.List;


/**
 * Created by marcote on 9/25/14.
 */
public class Program {
    public static void main(String[] args) {
        EntryPoint entryPoint = new EntryPoint("https://outlook.office365.com/EWS/OData/", new Credentials() {
            @Override
            public String getToken() {
                return "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6ImtyaU1QZG1Cdng2OHNrVDgtbVBBQjNCc2VlQSJ9.eyJhdWQiOiJodHRwczovL291dGxvb2sub2ZmaWNlMzY1LmNvbS8iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC81YWI5YWY5Yi00NTM0LTRjMzEtOGU1MC0xZTA5ODQ2MTQ4MWMvIiwiaWF0IjoxNDExNzY1MDYzLCJuYmYiOjE0MTE3NjUwNjMsImV4cCI6MTQxMTc2ODk2MywidmVyIjoiMS4wIiwidGlkIjoiNWFiOWFmOWItNDUzNC00YzMxLThlNTAtMWUwOTg0NjE0ODFjIiwiYW1yIjpbInB3ZCJdLCJvaWQiOiI5MTk5MzVjMy1lZGM4LTRhNDQtOWU3NC1mMGI4YzlmMWY0ODYiLCJ1cG4iOiJtYXJjb3N0QGxhZ2FzaC5jb20iLCJ1bmlxdWVfbmFtZSI6Im1hcmNvc3RAbGFnYXNoLmNvbSIsInN1YiI6Im5kbzRZYUZfVFN1TlE0LWlqeUk2UXNKYzNtUERoMzFwMUFweTh3dWw4Qm8iLCJwdWlkIjoiMTAwM0JGRkQ4M0NCQjExRSIsImZhbWlseV9uYW1lIjoidG9ycmVzIiwiZ2l2ZW5fbmFtZSI6Ik1hcmNvcyIsImFwcGlkIjoiNzc4YTA5OWUtZWQ2ZS00OWEyLTlmMTUtOTJjMDEzNjZhZDdkIiwiYXBwaWRhY3IiOiIwIiwic2NwIjoiQ2FsZW5kYXJzLlJlYWQgQ2FsZW5kYXJzLldyaXRlIENvbnRhY3RzLlJlYWQgQ29udGFjdHMuV3JpdGUgTWFpbC5SZWFkIE1haWwuU2VuZCBNYWlsLldyaXRlIHVzZXJfaW1wZXJzb25hdGlvbiIsImFjciI6IjEifQ.bDDUlwip14HZsjcTDDtJpbza_Sn_-qGa9qYumNcIEUqsLkKo2MZxnZttdyMhHwAE87WGNjaw3ya3pNvql0DPAM13DlWJxtYH4KE5CS69K1b3ymKjzVYYlDRInpQZMSDkyMw6bp1MD484M1qjO1OGnndzDGspxGQzIxpaxWfMQi00NiIbcyKoTs3dQkO3TLGuy0GVY_DF5C89i18AUfWrOwRNDvCCb5f2qh_soLoQUhW5Eo-_465qMvkaQuTHyusc65UJpoC8RUX74Y8nJ0fzs5gAF7K-AlIcjlnx0t-TX_MEFkoOFz8rXHKfv7QQQ9h5nZ7iaWDc2IZqf_BoW8drbQ";
            }
        }, new DependencyResolver());

        ListenableFuture<List<Message>> future = entryPoint.getMe().getMessages()
                .top(10)
                .skip(20)
                .execute();


        ListenableFuture<User> future2 = entryPoint.getMe().execute();

        // https://exchange.com/owa/me/messages?$top=10&skip=20
    }
}
