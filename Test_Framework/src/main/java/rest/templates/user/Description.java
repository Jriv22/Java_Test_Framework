package rest.templates.user;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Description {

    @SerializedName("urls")
    @Expose
    private List urls = null;

    public List getUrls() {
        return urls;
    }

    public void setUrls(List urls) {
        this.urls = urls;
    }

}

