package model;

public class DeveloperModel {
    private String name;
    private String  profile_pic;
    private String url;

    public DeveloperModel(String name, String profile_pic, String url) {
        this.name = name;
        this.profile_pic = profile_pic;
        this.url = url;
    }

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
