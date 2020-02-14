package tensorclient.datamodels;

public class LocationInfo {
    private String type;
    private String name;
    private String title;

    public LocationInfo(String type, String name, String title) {
        this.type = type;
        this.name = name;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
