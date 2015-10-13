package microsoft.com.unittests;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class LoginHelper {



    public static AuthenticationResult getAccessTokenFromUserCredentials(String resource,
                                                                          String username, String password) throws Exception {
        AuthenticationContext context;
        AuthenticationResult result = null;
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(1);
            context = new AuthenticationContext(Constants.AUTHORITY_URL, false, service);
            Future<AuthenticationResult> future = context.acquireToken(
                    resource, Constants.CLIENT_ID, username, password,
                    null);
            result = future.get();
        } finally {
            service.shutdown();
        }

        if (result == null) {
            throw new Exception(
                    "authentication result was null");
        }
        return result;
    }
}
