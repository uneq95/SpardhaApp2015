package com.ritesh.spardha.gcm;

/**
 * Created by ritesh_kumar on 07-Aug-15.
 */
public class GcmMessage {

    private int msgType;
    // elements for message type 1
    private String sport;
    private String location;
    private String date;
    private String time;
    private String team1;
    private String team2;

    //elements for message type 2
    private String photoLink;
    private String imageLinkedMsg;

    //elements for message type 3
    private String msgTitle;
    private String msgBody;


    public GcmMessage(int msgType, String sport, String location, String date, String time, String team1, String team2) {
        this.sport = sport;
        this.msgType = msgType;
        this.location = location;
        this.date = date;
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
    }

    public GcmMessage(int msgType, String photoLink, String imageLinkedMsg) {
        this.photoLink = photoLink;
        this.msgType = msgType;
        this.imageLinkedMsg = imageLinkedMsg;
    }

    public GcmMessage(int msgType, String msgTitle, String msgBody, boolean isMsgtype3) {
        this.msgTitle = msgTitle;
        this.msgBody = msgBody;
        this.msgType = msgType;
    }

    /* Message type 1    START  */
    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    /* Message type 1    END  */

    /* Message type 2    START  */

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getImageLinkedMsg() {
        return imageLinkedMsg;
    }

    public void setImageLinkedMsg(String imageLinkedMsg) {
        this.imageLinkedMsg = imageLinkedMsg;
    }

    /* Message type 2    END  */

    /* Message type 3    START  */
    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }


    /* Message type 3    END  */
    public int getMsgType() {
        return msgType;
    }
}
