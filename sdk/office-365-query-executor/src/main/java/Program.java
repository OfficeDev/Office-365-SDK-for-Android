import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.office365.query.DependencyResolver;
import com.microsoft.office365.query.EntryPoint;
import com.microsoft.office365.query.Message;
import com.microsoft.office365.query.QueryableFuture;

import java.util.List;


/**
 * Created by marcote on 9/25/14.
 */
public class Program {
    public static void main(String[] args) {
        EntryPoint entryPoint = new EntryPoint("https://exchange.com/owa", new DependencyResolver());

        ListenableFuture<List<Message>> future =
                entryPoint.getMe().getMessages().top(10).skip(20).execute(Message.class);

        // https://exchange.com/owa/me/messages?$top=10&skip=20
    }
}
