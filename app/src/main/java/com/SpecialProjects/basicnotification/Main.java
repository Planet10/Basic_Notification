package com.SpecialProjects.basicnotification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static android.support.v4.app.NotificationCompat.Builder;

public class Main extends Activity {
    NewMessageNotification newMessageNotification;
    /**
     * A numeric value that identifies the notification that we'll be sending.
     * This value needs to be unique within this app, but it doesn't need to be
     * unique system-wide.
     */
    Context context;

    public static final int NOTIFICATION_ID;

    static {
        NOTIFICATION_ID = 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        newMessageNotification = new NewMessageNotification();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendNotification(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lipsum.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        // BEGIN_INCLUDE (build_notification)
        /**
         * Use NotificationCompat.Builder to set up our notification.
         */
        Builder builder = new Builder(this);
        builder.setSmallIcon(R.drawable.ic_action_stat_share);
        //set the intent that will fire when user taps the notification
        builder.setContentIntent(pendingIntent);
        //set the notification to auto cancel
        builder.setAutoCancel(true);
        //build the notifications appearance
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
        builder.setContentTitle("Basic Notifications Sample");
        builder.setContentText("Time to learn about notifications");
        builder.setSubText("Tap to view documentation about notifications");

        //send the notification. This will immediately display the notification icon in the notification bar
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

}
