package cl.dmix.desafiolatam.doggappmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BreedImageListResponse {
    @SerializedName("message")
    private List<String> imageURL;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(List<String> imageURL) {
        this.imageURL = imageURL;
    }
}
