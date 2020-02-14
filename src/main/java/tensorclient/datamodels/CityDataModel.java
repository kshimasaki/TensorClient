package tensorclient.datamodels;

import com.google.gson.JsonObject;

public class CityDataModel extends LocationDataModel {
    private int stateId;
    private int countryId;
    private String countryCode;

    public CityDataModel(int id, LocationInfo info, int stateId, int countryId, String countryCode) {
        super(id, info);
        this.stateId = stateId;
        this.countryId = countryId;
        this.countryCode = countryCode;
    }

    public CityDataModel(JsonObject object) {
        super();
        String entryType = "city";
        String entryName = object.get("name").getAsString();
        String entryTitle = object.get("title").getAsString();
        this.setInfo(new LocationInfo(entryType, entryName, entryTitle));
        this.setId(object.get("id").getAsInt());
        this.stateId = object.get("state").getAsJsonObject().get("id").getAsInt();
        this.countryId = object.get("country").getAsJsonObject().get("id").getAsInt();
        this.countryCode = object.get("country").getAsJsonObject().get("code").getAsString();
    }

    public int getStateId() {
        return stateId;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
