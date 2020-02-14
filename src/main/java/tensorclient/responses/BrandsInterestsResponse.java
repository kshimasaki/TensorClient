package tensorclient.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandsInterestsResponse {
    @SerializedName("status")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private BrandsInterestsDataModel data;

    public BrandsInterestsResponse(boolean success, BrandsInterestsDataModel data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public BrandsInterestsDataModel getData() {
        return data;
    }

    public void setData(BrandsInterestsDataModel data) {
        this.data = data;
    }
}
