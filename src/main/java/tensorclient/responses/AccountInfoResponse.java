package tensorclient.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountInfoResponse {
    @SerializedName("user")
    @Expose
    private UserInfoModel user;

    public AccountInfoResponse(UserInfoModel user) {
        this.user = user;
    }

    public void setUser(UserInfoModel user) {
        this.user = user;
    }

    public UserInfoModel getUser() {
        return user;
    }
}

