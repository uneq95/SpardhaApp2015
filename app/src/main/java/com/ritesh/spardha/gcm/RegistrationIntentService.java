/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ritesh.spardha.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.ritesh.spardha.spardha2015.R;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};
    String TOKEN;
    private static final String SERVER_URL="http://spardusers.host56.com/gcmregister.php";

    public RegistrationIntentService() {
        super(TAG);
    }
    private SharedPreferences sharedPreferences;

    @Override
    protected void onHandleIntent(Intent intent) {
         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            // In the (unlikely) event that multiple refresh operations occur simultaneously,
            // ensure that they are processed sequentially.
            synchronized (TAG) {
                // [START register_for_gcm]
                // Initially this call goes out to the network to retrieve the token, subsequent calls
                // are local.
                // [START get_token]
                String token=sharedPreferences.getString(QuickstartPreferences.GCM_TOKEN, "");
                if(TextUtils.isEmpty(token)){
                    InstanceID instanceID = InstanceID.getInstance(this);
                     token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                            GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                    // [END get_token]
                    //Log.i(TAG, "GCM Registration Token: " + token);

                    // save gcm token in shared preferences
                    sharedPreferences.edit().putString(QuickstartPreferences.GCM_TOKEN,token).apply();
                    // TODO: Implement this method to send any registration to your app's servers.
                    if(!TextUtils.isEmpty(token))
                    if(!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER,false)){
                        sendRegistrationToServer(token);
                    }
                }
                TOKEN=token;
                Log.i(TAG, "GCM Registration Token: " + token);
                //Toast.makeText(getApplicationContext(),"token: "+token,Toast.LENGTH_LONG).show();
                /*if(!sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER,false)){
                    sendRegistrationToServer(token);
                }*/
                Log.i(TAG, "token sent to server " +sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER,false) );

                // Subscribe to topic channels
                subscribeTopics(token);

                // You should store a boolean that indicates whether the generated token has been
                // sent to your server. If the boolean is false, send the token to your server,
                // otherwise your server should have already received the token.

                //sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();


                // [END register_for_gcm]
            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    /**
     * Persist registration to third-party servers.
     *
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        new RegisterToServer(token).execute();
    }

    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        for (String topic : TOPICS) {
            GcmPubSub pubSub = GcmPubSub.getInstance(this);
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
    // [END subscribe_topics]

    private class RegisterToServer extends AsyncTask<Void,Void,String> {
        boolean SUCCESS= false;
        HttpClient httpClient;
        HttpPost httpPost;
        String response;
        NameValuePair regID;
        ArrayList<NameValuePair> regData;
        public RegisterToServer(String regToken){
            regID= new BasicNameValuePair("regID",regToken);
            regData = new ArrayList<>();
            regData.add(regID);
        }
        @Override
        protected String doInBackground(Void... params) {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(SERVER_URL);
            while(true){

                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(regData));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    response= httpClient.execute(httpPost,responseHandler);
                    SUCCESS=true;
                } catch (IOException e) {
                    e.printStackTrace();
                    SUCCESS=false;
                }
                if(SUCCESS) break;
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //parse the response from server and accordin to it save the sent to server shared preference
            if(SUCCESS&&!TextUtils.isEmpty(TOKEN))
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
            //Toast.makeText(getApplicationContext(), "response : " + s, Toast.LENGTH_LONG).show();

        }
    }
}
