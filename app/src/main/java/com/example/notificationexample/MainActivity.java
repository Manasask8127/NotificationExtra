package com.example.notificationexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button Simple,Download_progress,FullScreen,Expanded;

    public static final String CHANNEL_ID="Simple_Channel";
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,0);


        Simple=findViewById(R.id.simple);
        Download_progress=findViewById(R.id.download);
        FullScreen=findViewById(R.id.fullScreen);
        Expanded=findViewById(R.id.Expanded);

        Simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notifications)
                        .setContentTitle("Simple Notification")
                        .setContentText("Example for Simple Notification")
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);
                createNotificationChannel();
                notificationManager.notify(0,builder.build());
                //NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //manager.createNotificationChannel();
                //manager.notify(0, builder.build());


            }
        });
        Download_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                builder.setContentTitle("Picture Download")
                        .setContentText("Download in progress")
                        .setSmallIcon(R.drawable.notifications)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.image))
                        .setCategory(NotificationCompat.CATEGORY_ALARM)  //DND
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

// Issue the initial notification with zero progress
                int PROGRESS_MAX = 100;
                int PROGRESS_CURRENT = 0;
                builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
                notificationManager.notify(1, builder.build());

// Do the job here that tracks the progress.
// Usually, this should be in a
// worker thread
// To show progress, update PROGRESS_CURRENT and update the notification with:
// builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
// notificationManager.notify(notificationId, builder.build());

// When done, update the notification one more time to remove the progress bar
                builder.setContentText("Download complete")
                        .setProgress(0,0,false);
                notificationManager.notify(1, builder.build());

            }
        });

        FullScreen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
               /* Intent fullScreenIntent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(MainActivity.this, 0,
                        fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notifications)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setFullScreenIntent(fullScreenPendingIntent, true)
                        .setAutoCancel(true)
                        .setContentIntent(fullScreenPendingIntent);
                //int timestamp1=10,timestamp2=12,timestamp3=14,timestamp4=19;

                /*Notification notification = new Notification.Builder(MainActivity.this, CHANNEL_ID)
                        .setStyle(new NotificationCompat.MessagingStyle("Me")
                                .setConversationTitle("Team lunch")
                                .addMessage("Hi", timestamp1, (Person) null) // Pass in null for user.
                                .addMessage("What's up?", timestamp2, "Coworker")
                                .addMessage("Not much", timestamp3, null)
                                .addMessage("How about lunch?", timestamp4, "Coworker"))
                        .build();*/
                Intent fullScreenIntent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(MainActivity.this, 0,
                        fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.image)
                                .setContentTitle("Incoming call")
                                .setContentText("(919) 555-1234")
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setCategory(NotificationCompat.CATEGORY_CALL)

                                // Use a full-screen intent only for the highest-priority alerts where you
                                // have an associated activity that you would like to launch after the user
                                // interacts with the notification. Also, if your app targets Android 10
                                // or higher, you need to request the USE_FULL_SCREEN_INTENT permission in
                                // order for the platform to invoke this notification.
                                .setFullScreenIntent(fullScreenPendingIntent, true);

                Notification incomingCallNotification = notificationBuilder.build();

                createNotificationChannel();
                notificationManager.notify(2,incomingCallNotification);
            }
        });

       Expanded.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Notification notification;
              // createNotificationChannel();
              // notificationManager.notify(3,
               Notification notification=new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                       .setSmallIcon(R.drawable.notifications)
                       .setContentTitle("imageTitle")
                       .setContentText("imageDescription")
                       .setAutoCancel(true)
                       .setContentIntent(pendingIntent)
                       .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.image))
                        .setStyle(new NotificationCompat.MessagingStyle("Message")
                                .addMessage(new NotificationCompat.MessagingStyle.Message("Hi",10,"Me"))
                                .addMessage(new NotificationCompat.MessagingStyle.Message("Hello",10,"Other")))
                       .build();
                       /*.setStyle(new NotificationCompat.BigTextStyle()
                       .bigText("I implemented it the same way using BigView, i have one problem, " +
                               "on some phone sliding the notification to expand is bit difficult. " +
                               "We have to use the full finger to roll over the notification area(full surface area of finger"))*/

               ////.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.image))
               createNotificationChannel();
               notificationManager.notify(5,notification);

           }
       });



    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Sound";
            String description = "creates sound";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}