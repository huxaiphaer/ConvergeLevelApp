package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DeveloperModel {

    @SerializedName("items")
    @Expose
    private Items[] items;

    public Items[] getItems() {
        return items;
    }

}
