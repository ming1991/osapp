package net.oschina.app.bean;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 动弹实体类
 * 
 * @author liux (http://my.oschina.net/liux),kymjs(kymjs123@gmail.com)
 * @version 1.1 添加语音动弹功能
 * @created 2012-3-21
 * @changed 2014-12-1
 */
@SuppressWarnings("serial")
@XStreamAlias("tweet")
public class Tweet extends Entity implements Parcelable {

    @XStreamAlias("portrait")
    private String portrait;
    @XStreamAlias("author")
    private String author;
    @XStreamAlias("authorid")
    private int authorid;
    @XStreamAlias("body")
    private String body;
    @XStreamAlias("appclient")
    private int appclient;
    @XStreamAlias("commentCount")
    private String commentCount;
    @XStreamAlias("pubDate")
    private String pubDate;
    @XStreamAlias("imgSmall")
    private String imgSmall;
    @XStreamAlias("imgBig")
    private String imgBig;
    @XStreamAlias("attach")
    private String attach;

    @XStreamAlias("likeCount")
    private int likeCount;

    @XStreamAlias("isLike")
    private int isLike;

    @XStreamAlias("likeList")
    private List<User> likeUser = new ArrayList<User>();

    private String imageFilePath;
    private String audioPath;

    public Tweet() {}

    public Tweet(Parcel dest) {
        id = dest.readInt();
        portrait = dest.readString();
        author = dest.readString();
        authorid = dest.readInt();
        body = dest.readString();
        appclient = dest.readInt();
        commentCount = dest.readString();
        pubDate = dest.readString();
        imgSmall = dest.readString();
        imgBig = dest.readString();
        attach = dest.readString();
        imageFilePath = dest.readString();
        audioPath = dest.readString();
        isLike = dest.readInt();
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getAppclient() {
        return appclient;
    }

    public void setAppclient(int appclient) {
        this.appclient = appclient;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    public String getImgBig() {
        return imgBig;
    }

    public void setImgBig(String imgBig) {
        this.imgBig = imgBig;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public List<User> getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(List<User> likeUser) {
        this.likeUser = likeUser;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(portrait);
        dest.writeString(author);
        dest.writeInt(authorid);
        dest.writeString(body);
        dest.writeInt(appclient);
        dest.writeString(commentCount);
        dest.writeString(pubDate);
        dest.writeString(imgSmall);
        dest.writeString(imgBig);
        dest.writeString(attach);
        dest.writeString(imageFilePath);
        dest.writeString(audioPath);
        dest.writeInt(isLike);
    }

    public static final Parcelable.Creator<Tweet> CREATOR = new Creator<Tweet>() {

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }

        @Override
        public Tweet createFromParcel(Parcel source) {
            return new Tweet(source);
        }
    };


}
