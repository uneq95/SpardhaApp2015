/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ritesh.spardha.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;
import com.ritesh.spardha.spardha2015.R;
import com.ritesh.spardha.spardha2015.SpardhaHomeTest;

import java.util.ArrayList;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    GcmMessageQueueDatabase db;

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        db = new GcmMessageQueueDatabase(this);
        //String message = data.getString("message");
        GcmMessage msg = parseGcmMessage(data);
        switch (msg.getMsgType()) {

            case 1:
                if(checkSettings(msg.getSportId())){
                    sendNotification1(msg);
                    db.open();
                    db.insertMsgType1(msg);
                    db.close();
                }

                break;
            case 2:
                sendNotification2(msg);
                db.open();
                db.insertMsgType2(msg);
                db.close();
                break;
            case 3:
                sendNotification3(msg);
                db.open();
                db.insertMsgType3(msg);
                db.close();
                break;

        }


        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */


        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     * <p/>
     * message GCM message received.
     */
    private void sendNotification1(GcmMessage msg) {
        System.out.println();
        String match = String.format("%s : %s", msg.getSport(), msg.getTime());
        String title = String.format("%s VS %s", msg.getTeam1(), msg.getTeam2());
        Intent intent = new Intent(this, SpardhaHomeTest.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle(title)
                .setContentText(match)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification2(GcmMessage msg) {
        Intent intent = new Intent(this, SpardhaHomeTest.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.app_icon)
                .setContentText(msg.getImageLinkedMsg())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification3(GcmMessage msg) {
        Intent intent = new Intent(this, SpardhaHomeTest.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle(msg.getMsgTitle())
                .setContentText(msg.getMsgBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }


    private GcmMessage parseGcmMessage(Bundle data) {
        GcmMessage msg = null;
        System.out.println("bundle data : "+data);
        System.out.println(data.getString("msg_type"));
        int msgType = Integer.parseInt(data.getString("msg_type"));

        switch (msgType) {
            case 1:
                String sport = data.getString("sport");
                String location = data.getString("location");
                String date = data.getString("date");
                String time = data.getString("time");
                String team1 = data.getString("team1");
                String team2 = data.getString("team2");
                String team1ImgLink =data.getString("team1ImgLink");
                String sportId =data.getString("sportId");
                System.out.println("link 1 : "+data.getString("team1ImgLink"));
                String team2ImgLink =data.getString("team2ImgLink");
                msg = new GcmMessage(1, sport, location, date, time, team1, team2,team1ImgLink,team2ImgLink,sportId);
                break;
            case 2:
                String imageLink = data.getString("imageLink");
                String imageLinkedMsg = data.getString("imageMsg");
                msg = new GcmMessage(2, imageLink, imageLinkedMsg);
                break;
            case 3:
                String msgTitle = data.getString("message");
                String msgBody = data.getString("body");
                msg = new GcmMessage(3, msgTitle, msgBody, true);
                break;
        }
        return msg;
    }

    private boolean checkSettings(String sportsId){
        int sportsIdInInteger=Integer.parseInt(sportsId);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getBoolean(Integer.toString(sportsIdInInteger),true);

    }
}
