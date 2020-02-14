package tensorclient.datamodels;

public abstract class LocationDataModel {
    private int id;
    private LocationInfo info;

    public LocationDataModel(int id, LocationInfo info) {
        this.id = id;
        this.info = info;
    }

    public LocationDataModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocationInfo getInfo() {
        return info;
    }

    public void setInfo(LocationInfo info) {
        this.info = info;
    }
}
