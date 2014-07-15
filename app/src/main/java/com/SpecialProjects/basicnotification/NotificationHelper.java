package com.SpecialProjects.basicnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by David Smith on 7/15/2014.
 */
public class NotificationHelper {
    private int Id;
    private Notification notification;
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;
    private CharSequence ContentTitle;
    private Context context;

    //Constructor takes context and unique Id
    public NotificationHelper(Context context,int id){
        this.Id = id;
        this.context = context;
    }
    public void createNotification(){
        //get instance of notificationManager
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //set notification icon and text
        CharSequence initialMenuBarText = "Start of task";
        int iconToShow = android.R.drawable.stat_sys_download;
        long when = System.currentTimeMillis();
       // notification = new Notification(iconToShow,initialMenuBarText,when);
        //We get full control over the title and subtitle that is displayed in the pulldown notification area
        // This is the title of the notification
        ContentTitle = "Long running process";
        //Text below the Title
        CharSequence contentText = "Our task has started";
        //We can set up a pending intent on the notification so that once clicked we carry out some other action. We will call another activity
        //First we create an intent object passing in our context and the activity that we want to open
       // Intent notificationIntent = new Intent(context, AnotherActivity.class);
        //open only one instance of AnotherActivity if notification touched -- FLAG_ACTIVITY_SINGLE_TOP
        //notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
       // pendingIntent  = PendingIntent.getActivity(context,0,notificationIntent,0);
        //add the titles and pending intent to our notification object
       // notification.setLatestEventInfo()
        final Resources res = context.getResources();
        final Bitmap largeIcon = BitmapFactory.decodeResource(res,R.drawable.ic_launcher);
        final String ticker = "ticker text";
        final String title = "Long running process";
        final String text = "our task has started";
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setSmallIcon(R.drawable.ic_action_stat_share);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLargeIcon(largeIcon);
        builder.setTicker(ticker);

        builder.setContentIntent(PendingIntent.getActivity(context,0,new Intent(Intent.ACTION_VIEW, Uri.parse("http://lipsum.com")),PendingIntent.FLAG_UPDATE_CURRENT));
        // Show expanded text content on devices running Android 4.1 or
        // later.
        builder.setStyle(new NotificationCompat.BigTextStyle()
            .bigText(text)
            .setBigContentTitle(title)
            .setSummaryText("Dummy summary text"));
        // Example additional actions for this notification. These will
        // only show on devices running Android 4.1 or later, so you
        // should ensure that the activity in this notification's
        // content intent provides access to the same actions in
        // another way.
        builder.addAction(R.drawable.ic_action_stat_share,"Share",PendingIntent.getActivity(context,0,Intent.createChooser(new Intent(Intent.ACTION_SEND)
                                                                                                                            .setType("text/plain")
                                                                                                                            .putExtra(Intent.EXTRA_TEXT,"Dummy text"),"Dummy Title"),
                                                                                                                            PendingIntent.FLAG_UPDATE_CURRENT));
        builder.addAction(R.drawable.ic_action_stat_reply,"Reply",null);
        // Automatically dismiss the notification when it is touched.
        builder.setAutoCancel(true);

    }

}
