package com.emergencyguide.Entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author DarckLH
 * @date 2021/5/22 19:29
 * @Description
 */
public class Post {

    private int postid;
    private String topic;
    private String content;
    private int likes;
    private int dislike;
    private int reply;
    private String status;
    private Timestamp time;
    private int openid;
    private String customername;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getOpenid() {
        return openid;
    }

    public void setOpenid(int openid) {
        this.openid = openid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postid=" + postid +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", dislike=" + dislike +
                ", reply=" + reply +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", openid=" + openid +
                ", customername=" + customername +
                '}';
    }
}
