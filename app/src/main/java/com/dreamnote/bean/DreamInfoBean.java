package com.dreamnote.bean;

/**
 * @author: Administrator
 * @time 2017-02-14 15:36
 * @email 770164810@qq.com
 */

public class DreamInfoBean {

    /**
     * avatar : null
     * name : Timer
     * content : 跟着在旅途中遇到的姐姐，走进一家日式房间。里面有一张圆桌，摆了一锅粥。我们每个人分到一碗。喝着喝着，开始了欢送会。原来就是这个姐姐的澳洲毕业欢送会。
     * isLike : 1
     * userId : 111
     * releaseTime : 2017-1-1
     * picture : null
     * commentNum : 2
     */

    private Object avatar;
    private String name;
    private String content;
    private String isLike;
    private String userId;
    private String releaseTime;
    private Object picture;
    private String commentNum;

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }
}
