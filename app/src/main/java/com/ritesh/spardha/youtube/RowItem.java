package com.ritesh.spardha.youtube;

public class RowItem {

	String title;
	String videoId;
	String thumbUrl;
	
	public RowItem(String title,String videoId,String thumbUrl){
		this.title=title;
		this.videoId=videoId;
		this.thumbUrl=thumbUrl;
	}
	
	public String getTitle(){
		return title;
	}
	public String getVideoId(){
		return videoId;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	public void setVideoId(String id){
		this.videoId=id;
	}
	public String getThumbUrl(){
		return thumbUrl;
	}
	public void setThumbUrl(String url){
		this.thumbUrl=url;
	}
	
}
