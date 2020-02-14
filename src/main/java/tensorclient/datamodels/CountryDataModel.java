package tensorclient.datamodels;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CountryDataModel extends LocationDataModel {
    private int countryId;
    private String countryCode;

    public CountryDataModel(int id, LocationInfo info, int countryId, String countryCode) {
        super(id, info);
        this.countryId = countryId;
        this.countryCode = countryCode;
    }

    public CountryDataModel(JsonObject object) {
        super();
        String entryType = "country";
        String entryName = object.get("name").getAsString();
        String entryTitle = object.get("title").getAsString();
        this.setInfo(new LocationInfo(entryType, entryName, entryTitle));
        this.setId(object.get("id").getAsInt());
        this.countryId = object.get("country").getAsJsonObject().get("id").getAsInt();
        this.countryCode = object.get("country").getAsJsonObject().get("id").getAsString();
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
