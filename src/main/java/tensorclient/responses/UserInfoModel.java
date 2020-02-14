package tensorclient.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("credits")
    @Expose
    private UserCreditsModel credits;

    public UserInfoModel(String name, String email, UserCreditsModel credits) {
        this.name = name;
        this.email = email;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserCreditsModel getCredits() {
        return credits;
    }
}
