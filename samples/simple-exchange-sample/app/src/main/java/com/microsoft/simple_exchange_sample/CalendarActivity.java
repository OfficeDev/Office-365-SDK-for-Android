package com.microsoft.simple_exchange_sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.microsoft.office365.exchange.services.Attendee;
import com.microsoft.office365.exchange.services.Event;
import com.microsoft.office365.odata.EntityContainerClient;
import com.microsoft.office365.odata.impl.DefaultDependencyResolver;

import java.util.List;


public class CalendarActivity extends Activity implements View.OnTouchListener {

    TextView subject;
    TextView attendees;
    TextView location;
    ScrollView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        this.subject = (TextView)findViewById(R.id.tv_calendar_subject);
        this.attendees = (TextView)findViewById(R.id.tv_calendar_attendees);
        this.location = (TextView)findViewById(R.id.tv_calendar_location);
        this.content = (ScrollView)findViewById(R.id.sv_calendar_content);
        this.content.setOnTouchListener(this);

        getNextEvent();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = MotionEventCompat.getActionMasked(motionEvent);

        switch(action) {
            case (MotionEvent.ACTION_UP) :
                getNextEvent();
                return true;
            case (MotionEvent.ACTION_DOWN) :
                getPreviousEvent();
                return true;
            default :
                return super.onTouchEvent(motionEvent);
        }
    }

    private Event getNextEvent() {

        EntityContainerClient client = new EntityContainerClient(Constants.ENDPOINT_ID, (DefaultDependencyResolver)Controller.getInstance().getDependencyResolver());

        ListenableFuture<List<Event>> events = client
                .getMe()
                .getCalendars().getById("Calendar")
                .getEvents()
                .top(1)
                .execute();

        // handle success and failure cases
        Futures.addCallback(events, new FutureCallback<List<Event>>() {

            @Override
            public void onSuccess(final List<Event> result) {

               update(result.get(0));
            }

            @Override
            public void onFailure(final Throwable t) {
                Controller.handleError(CalendarActivity.this, t.getMessage());
            }
        });

        return null;
    }

    private Event getPreviousEvent() {

        return null;
    }

    private void update(final Event event) {
        final StringBuilder sb = new StringBuilder();
        for (Attendee a : event.getAttendees()) {
            sb.append(a.getEmailAddress().getName() + ", ");
        }
        // remove last comma
        sb.deleteCharAt(sb.length() - 2);

        final TextView subject = this.subject;
        final TextView attendees = this.attendees;
        final TextView location = this.location;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                subject.setText(event.getSubject());
                attendees.setText(sb.toString());
                location.setText(event.getLocation().getDisplayName());
            }
        });
    }
}
