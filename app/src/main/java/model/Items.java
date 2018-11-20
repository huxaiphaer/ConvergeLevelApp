package model;

import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("login")
    private String name;

    @SerializedName("avatar_url")
    private String  profile_pic;

    @SerializedName("url")
    private String url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
