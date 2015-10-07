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
    private String team1ImgLink;
    private String team2ImgLink;
    private String sportId;

    //elements for message type 2
    private String photoLink;
    private String imageLinkedMsg;

    //elements for message type 3
    private String msgTitle;
    private String msgBody;


    public GcmMessage(int msgType, String sport, String location, String date, String time, String team1, String team2, String team1ImgLink, String team2ImgLink,String sportId) {
        this.sport = sport;
        this.msgType = msgType;
        this.location = location;
        this.date = date;
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
        this.team1ImgLink = team1ImgLink;
        this.team2ImgLink = team2ImgLink;
        this.sportId=sportId;
    }
    public GcmMessage(int msgType, String sport, String location, String date, String time, String team1, String team2, String team1ImgLink, String team2ImgLink) {
        this.sport = sport;
        this.msgType = msgType;
        this.location = location;
        this.date = date;
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
        this.team1ImgLink = team1ImgLink;
        this.team2ImgLink = team2ImgLink;
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

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTeam1ImgLink() {
        return team1ImgLink;
    }

    public String getTeam2ImgLink() {
        return team2ImgLink;
    }


    /* Message type 1    END  */

    /* Message type 2    START  */

    public String getPhotoLink() {
        return photoLink;
    }

    public String getImageLinkedMsg() {
        return imageLinkedMsg;
    }

    /* Message type 2    END  */

    /* Message type 3    START  */
    public String getMsgTitle() {
        return msgTitle;
    }

    public String getMsgBody() {
        return msgBody;
    }
    /* Message type 3    END  */

    public int getMsgType() {
        return msgType;
    }

    public String getSportId(){return sportId;}
}
