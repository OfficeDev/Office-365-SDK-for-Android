package com.microsoft.services.samples.outlook;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.outlookservices.Event;
import com.microsoft.outlookservices.orc.OutlookClient;
import com.microsoft.services.controllers.AsyncController;
import com.microsoft.services.controllers.AuthenticationController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;


public class CalendarActivity extends Activity implements View.OnClickListener {

    TextView subject;
    TextView time;
    TextView location;
    TextView body;
    int eventIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        this.subject = (TextView)findViewById(R.id.tv_calendar_subject);
        this.time = (TextView)findViewById(R.id.tv_calendar_time);
        this.location = (TextView)findViewById(R.id.tv_calendar_location);
        this.body = (TextView)findViewById(R.id.tv_calendar_event_body);

        Button previous = (Button)findViewById(R.id.button_calendar_previous);
        previous.setOnClickListener(this);
        Button next = (Button)findViewById(R.id.button_calendar_next);
        next.setOnClickListener(this);

        getEvent(true);
    }

    /**
     * handles actions for buttons on the view
     * @param view
     */
    @Override
    public void onClick(View view) {

        Boolean choice = true;
        switch (view.getId()) {
            case R.id.button_calendar_previous:
            {
                choice = false;
                break;
            }
            default:
                break;
        }

        final Boolean next = choice;
        AsyncController.getInstance().postAsyncTask(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                getEvent(next);
                return null;
            }
        });
    }

    private void getEvent(final Boolean next) {

        // create a client object
        OutlookClient client = new OutlookClient(ServiceConstants.ENDPOINT_ID, AuthenticationController.getInstance().getDependencyResolver());

        // fetch next batch of events and select the first only
        ListenableFuture<List<Event>> events = client
                .getMe()
                .getCalendar()
                .getEvents()
                .read();

        // handle success and failure cases
        Futures.addCallback(events, new FutureCallback<List<Event>>() {

            @Override
            public void onSuccess(final List<Event> result) {
                if (result.isEmpty()) {
                    displayEmptyCalendarMessage(next);
                }

                Collections.sort(result, new Comparator<Event>() {
                    @Override
                    public int compare(Event event, Event event2) {
                        if (event.getStart().getTime().getTime() < event2.getStart().getTime().getTime()) {
                            return -1;
                        }
                        else if (event.getStart().getTime().getTime() > event2.getStart().getTime().getTime()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });

                update(result, next);
            }

            @Override
            public void onFailure(final Throwable t) {
                AsyncController.getInstance().handleError(CalendarActivity.this, t.getMessage());
            }
        });
    }

    private void update(final List<Event> events, boolean next) {

        if (next) {
            ++this.eventIndex;
            if (this.eventIndex >= events.size()) {
                this.eventIndex = events.size() - 1;
            }
        }
        else {
            this.eventIndex--;
            if (this.eventIndex < 0) {
                this.eventIndex = 0;
            }
        }

        final Event event = events.get(this.eventIndex);
        final TextView subject = this.subject;
        final TextView time = this.time;
        final TextView location = this.location;
        final TextView body = this.body;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                subject.setText(event.getSubject());
                time.setText(event.getStart().getTime().toString());
                location.setText(event.getLocation().getDisplayName());
                body.setText(Html.fromHtml(event.getBody().getContent()));
            }
        });
    }

    private void displayEmptyCalendarMessage(Boolean lookingAtNext) {

        final String next = "further";
        final String previous = "previous";

        final String message =
                String.format(
                        "There are no %s events on your calendar",
                        lookingAtNext ? next : previous);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(
                        CalendarActivity.this,
                        message,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
