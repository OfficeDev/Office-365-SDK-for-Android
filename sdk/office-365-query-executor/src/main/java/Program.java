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
        EntryPoint entryPoint = new EntryPoint("https://exchange.com/owa", new Credentials() {
            @Override
            public String getToken() {
                return null;
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
