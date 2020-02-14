package tensorclient.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BrandsInterestsDataModel {
    @SerializedName("interests")
    @Expose
    private List<InterestsEntryModel> interests;
    @SerializedName("brands")
    @Expose
    private List<BrandsEntryModel> brands;

    public BrandsInterestsDataModel(List<InterestsEntryModel> interests, List<BrandsEntryModel> brands) {
        this.interests = interests;
        this.brands = brands;
    }

    public List<InterestsEntryModel> getInterests() {
        return interests;
    }

    public List<BrandsEntryModel> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandsEntryModel> brands) {
        this.brands = brands;
    }

    public void setInterests(List<InterestsEntryModel> interests) {
        this.interests = interests;
    }
}
