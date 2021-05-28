package com.emergencyguide.Entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author DarckLH
 * @date 2021/5/24 11:52
 * @Description
 */
public class Comment {

    private long commentid;
    private int replyid;
    private String content;
    private int likes;
    private int dislike;
    private int reply;
    private String status;
    private Timestamp time;
    private String customerid;
    private String customername;
    private String replytopic;

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public int getReplyid() {
        return replyid;
    }

    public void setReplyid(int replyid) {
        this.replyid = replyid;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getReplytopic() {
        return replytopic;
    }

    public void setReplytopic(String replytopic) {
        this.replytopic = replytopic;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentid=" + commentid +
                ", replyid=" + replyid +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", dislike=" + dislike +
                ", reply=" + reply +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", customerid=" + customerid +
                ", customername='" + customername + '\'' +
                ", replytopic='" + replytopic + '\'' +
                '}';
    }

}
