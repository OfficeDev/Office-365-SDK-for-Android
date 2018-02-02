package com.microsoft.mygraphapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.microsoft.services.graph.Attendee;
import com.microsoft.services.graph.BodyType;
import com.microsoft.services.graph.DateTimeTimeZone;
import com.microsoft.services.graph.EmailAddress;
import com.microsoft.services.graph.Event;
import com.microsoft.services.graph.Importance;
import com.microsoft.services.graph.ItemBody;
import com.microsoft.services.graph.Message;
import com.microsoft.services.graph.fetchers.GraphServiceClient;
import com.microsoft.services.orc.auth.AuthenticationCredentials;
import com.microsoft.services.orc.core.DependencyResolver;
import com.microsoft.services.orc.http.Credentials;
import com.microsoft.services.orc.http.impl.OAuthCredentials;
import com.microsoft.services.orc.http.impl.OkHttpTransport;
import com.microsoft.services.orc.serialization.impl.GsonSerializer;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

public class EventsActivity extends AppCompatActivity {

    private AuthenticationContext mAuthContext;
    private DependencyResolver mResolver;
    private GraphServiceClient mClient;
    private Event mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        final Button buttonMarkRead = (Button) findViewById(R.id.buttonMove);
        buttonMarkRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                moveEvent();
            }
        });

        loginProcess();
    }

    protected void loginProcess(){
        try {
            Futures.addCallback(logon(), new FutureCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    mClient = new GraphServiceClient(getString(R.string.EndpointUrl), mResolver);
                    createEvent();
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("logon", t.getMessage());
                }
            });

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    protected SettableFuture<Boolean> logon() throws NoSuchPaddingException, NoSuchAlgorithmException {
        final SettableFuture<Boolean> result = SettableFuture.create();

        try {
            mAuthContext = new AuthenticationContext(this, getString(R.string.AADAuthority), true);
            mAuthContext.acquireToken(
                    this,
                    getString(R.string.AADResourceId),
                    getString(R.string.AADClientId),
                    getString(R.string.AADRedirectUrl),
                    PromptBehavior.Auto,
                    new AuthenticationCallback<AuthenticationResult>() {

                        @Override
                        public void onSuccess(final AuthenticationResult authenticationResult) {
                            if (authenticationResult != null
                                    && authenticationResult.getStatus() == AuthenticationResult.AuthenticationStatus.Succeeded) {
                                mResolver = new DependencyResolver.Builder(
                                        new OkHttpTransport(), new GsonSerializer(),
                                        new AuthenticationCredentials() {
                                            @Override
                                            public Credentials getCredentials() {
                                                return new OAuthCredentials(authenticationResult.getAccessToken());
                                            }
                                        }).build();
                                result.set(true);
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            result.setException(e);
                        }

                    });
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            result.setException(e);
        }
        return result;
    }

    protected void createEvent(){
        Event myEvent = getSampleEventForToday();
        Futures.addCallback(
                mClient.getMe()
                        .getEvents()
                        .add(myEvent),
                new FutureCallback<Event>() {
                    @Override
                    public void onSuccess(final Event result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mEvent = result;
                                refreshView();
                            }
                        });
                    }

                    @Override
                    public void onFailure(final Throwable t) {
                        Log.e("getMessages", t.getMessage());
                    }
                });
    }

    private void refreshView() {

        TextView subject = (TextView) findViewById(R.id.textSubject);
        subject.setText(mEvent.getSubject());

        TextView attendees = (TextView) findViewById(R.id.textAttendees);
        attendees.setText(mEvent.getAttendees().size() + " people invited.");

        TextView start = (TextView) findViewById(R.id.textStart);
        start.setText(mEvent.getStart().getDateTime());

        TextView end = (TextView) findViewById(R.id.textEnd);
        end.setText(mEvent.getEnd().getDateTime());

        TextView body = (TextView) findViewById(R.id.textBody);
        body.setText(mEvent.getBody().getContent());
    }

    private void moveEvent(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        DateTimeTimeZone dtz = new DateTimeTimeZone();
        try {
            Date dateStart = formatter.parse(mEvent.getStart().getDateTime());

            Calendar cStart = Calendar.getInstance();
            cStart.setTime(dateStart);
            cStart.add(java.util.Calendar.HOUR, 2);
            dtz.setDateTime(formatter.format(cStart.getTime()));
            dtz.setTimeZone("UTC");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        DateTimeTimeZone dtzEnd = new DateTimeTimeZone();
        try {
            Date dateEnd = formatter.parse(mEvent.getEnd().getDateTime());

            Calendar cEnd = Calendar.getInstance();
            cEnd.setTime(dateEnd);
            cEnd.add(java.util.Calendar.HOUR, 2);
            dtzEnd.setDateTime(formatter.format(cEnd.getTime()));
            dtzEnd.setTimeZone("UTC");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mEvent.setStart(dtz);
        mEvent.setEnd(dtzEnd);

        Futures.addCallback(mClient.getMe()
                        .getEvents()
                        .getById(mEvent.getId())
                        .update(mEvent),
                new FutureCallback<Event>() {
                    @Override
                    public void onSuccess(final Event result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mEvent = result;
                                refreshView();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("MyGraphApp", t.getMessage());
                    }
                });
    }

    private Event getSampleEventForToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedTime = formatter.format(new Date());

        DateTimeTimeZone dtz = new DateTimeTimeZone();
        dtz.setDateTime(formattedTime);
        dtz.setTimeZone("UTC");

        DateTimeTimeZone dtzEnd = new DateTimeTimeZone();

        java.util.Calendar dateEnd = java.util.Calendar.getInstance();
        dateEnd.add(java.util.Calendar.HOUR, 6);

        String formattedTimeEnd = formatter.format(dateEnd.getTime());
        dtzEnd.setDateTime(formattedTimeEnd);
        dtzEnd.setTimeZone("UTC");

        Event event = new Event();
        event.setSubject("Today's appointment");
        event.setStart(dtz);
        event.setEnd(dtzEnd);
        event.setImportance(Importance.high);

        //Event body
        ItemBody itemBody = new ItemBody();
        itemBody.setContent("This is the appointment info");
        itemBody.setContentType(BodyType.text);

        event.setBody(itemBody);

        //Attendees
        Attendee attendee1 = new Attendee();
        EmailAddress email = new EmailAddress();
        email.setAddress(getString(R.string.AttendeeMail1));
        attendee1.setEmailAddress(email);

        Attendee attendee2 = new Attendee();
        EmailAddress email2 = new EmailAddress();
        email2.setAddress(getString(R.string.AttendeeMail2));
        attendee2.setEmailAddress(email2);

        List<Attendee> listAttendees = new ArrayList<Attendee>();
        listAttendees.add(attendee1);
        listAttendees.add(attendee2);
        event.setAttendees(listAttendees);

        return event;
    }
}
