package com.ritesh.spardha.youtube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ritesh.spardha.spardha2015.LocationActivity;
import com.ritesh.spardha.spardha2015.R;


public class YtpChannel extends AppCompatActivity {
	

    ListView channelVideoList;
    String[] vidList;
    String[] videoId;
    String[] thumbnailUrl;
    List<RowItem> rowItem;
	Toolbar toolbar;
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.ytpchannel);
		progressDialog= new ProgressDialog(this);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			getSupportActionBar().setElevation(10);
			getSupportActionBar().setHomeButtonEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		channelVideoList =(ListView)findViewById(R.id.lvChannelVideoList);
		
		if(isNetworkConnected()){
			DownloadChannelList download =new DownloadChannelList(this);
	     	download.execute();
	     	getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	     	
	     	
		}else{
			/*LinearLayout ytpLayout=(LinearLayout)findViewById(R.id.ytpchannelLayout);
	     	ytpLayout.setVisibility(View.GONE);*/

			Toast.makeText(getBaseContext(), "No Internet Access!",  Toast.LENGTH_LONG).show();
		}

		
	}
	
	class DownloadChannelList extends AsyncTask<Void, Void,Void>{

       Context context;
       
       public DownloadChannelList(Context c) {
		// TODO Auto-generated constructor stub
    	   
    	   this.context=c;
	   }

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if(!progressDialog.isShowing()){
				progressDialog.setMessage("Retrieving videos from Spardha YouTube Channel ...");
				progressDialog.setCancelable(false);
				progressDialog.show();
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				HttpClient client = new DefaultHttpClient();
				Log.d("http","http request set");
				HttpUriRequest request = new HttpGet("https://www.googleapis.com/youtube/v3/search?key=AIzaSyAAJfgyG3JQ0jm-uYUdx4BD-06MezIIVSI&channelId=UCA8-Lv8lTufkY507CUonbDQ&part=snippet,id&order=date&maxResults=20&type=video");
				//HttpUriRequest request = new HttpGet("https://gdata.youtube.com/feeds/api/videos?author=technexiitbhu&v=2&alt=jsonc");
				HttpResponse response = client.execute(request);
				Log.d("http response","http request executed");
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity);
				Log.d("json object", "going to initialize object");
				JSONObject json = new JSONObject(result);
				System.out.println("json : "+json);
				 final JSONArray jsonArray = json.getJSONArray("items");
				 int length= jsonArray.length();
				 String [] array = new String[length];
				 String[] channelId = new String[length] ;
				 String[] thumbId = new String[length];
				
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String title = jsonObject.getJSONObject("snippet").getString("title");
					array[i]=title;
					String id = jsonObject.getJSONObject("id").getString("videoId");
					channelId[i]=id;
					String url = jsonObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");
					thumbId[i]=url;
					
				    
				}
				vidList=array.clone();
				videoId=channelId.clone();
				thumbnailUrl=thumbId.clone();
				
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(),"Connection Error! Please try again!",Toast.LENGTH_SHORT).show();
					}
				});
			}
			
			return null;
		}
		
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub

			
			try {
				/*LinearLayout ll =(LinearLayout)((Activity) context)
						.findViewById(R.id.progressLayout);
				ll.setVisibility(View.GONE);*/
				rowItem = new ArrayList<RowItem>();
				for(int i=0;i<vidList.length;i++){
					RowItem item =new RowItem(vidList[i], videoId[i], thumbnailUrl[i]);
					rowItem.add(item);
				}
				
				CustomListAdapter listAdapter= new CustomListAdapter(YtpChannel.this, rowItem,thumbnailUrl);
				channelVideoList.setAdapter(listAdapter);
				channelVideoList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View v, int position,
							long id) {
						// TODO Auto-generated method stub
						//Toast.makeText(getBaseContext(), "video id "+position+"="+videoId[position], Toast.LENGTH_SHORT).show();
						Intent intentToPlayer= new Intent(YtpChannel.this,YoutubePlayer.class);
						intentToPlayer.putExtra("selected video id",videoId[position]);
						intentToPlayer.putExtra("video list", vidList);
						intentToPlayer.putExtra("thumbnail url",thumbnailUrl );
						intentToPlayer.putExtra("videoID", videoId);
						startActivity(intentToPlayer);
						
					}
				});
				//getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				super.onPostExecute(result);
			} catch (Exception e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(progressDialog.isShowing()){
				progressDialog.dismiss();
			}
		}
		
	}
	boolean isNetworkConnected(){
		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo()!=null);
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			NavUtils.navigateUpFromSameTask(this);
		}
		if (item.getItemId() == R.id.action_refresh) {
			DownloadChannelList download =new DownloadChannelList(this);
			download.execute();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_refresh, menu);
		return true;
	}


}
