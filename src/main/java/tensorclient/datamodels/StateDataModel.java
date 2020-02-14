package tensorclient.datamodels;

import com.google.gson.JsonObject;

/*
NOTE: There are no states yet. This will be updated as Tensor API is updated.
 */
public class StateDataModel extends LocationDataModel {
    private int stateId;
    private int countryId;
    private int countryCode;

    public StateDataModel(int id, LocationInfo info, int stateId, int countryId, int countryCode) {
        super(id, info);
        this.stateId = stateId;
        this.countryId = countryId;
        this.countryCode = countryCode;
    }

    public StateDataModel(JsonObject object) {
        super();
        String entryType = "state";
        String entryName = object.get("name").getAsString();
        String entryTitle = object.get("title").getAsString();
        this.setInfo(new LocationInfo(entryType, entryTitle, entryTitle));
        this.setId(object.get("id").getAsInt());
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }
}
