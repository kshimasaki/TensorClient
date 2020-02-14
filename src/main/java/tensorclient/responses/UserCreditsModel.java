package tensorclient.responses;

public class UserCreditsModel {
    private int reports;
    private int exports;
    private int posts;

    public UserCreditsModel(int reports, int exports, int posts) {
        this.reports = reports;
        this.exports = exports;
        this.posts = posts;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public int getExports() {
        return exports;
    }

    public void setExports(int exports) {
        this.exports = exports;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }
}
