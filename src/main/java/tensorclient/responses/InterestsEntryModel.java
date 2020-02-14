package tensorclient.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InterestsEntryModel {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;

    public InterestsEntryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
