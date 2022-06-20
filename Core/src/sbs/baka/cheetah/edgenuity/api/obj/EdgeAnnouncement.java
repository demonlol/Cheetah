package sbs.baka.cheetah.edgenuity.api.obj;

import org.json.*;

public class EdgeAnnouncement {

    private String title, contents, date;

    public EdgeAnnouncement(JSONObject object) {
        this.title = object.getString("title");
        this.contents = object.getString("contents");
        this.date = object.getString("date");
    }

    public String getTitle() { return title; }
    public String getContents() { return contents; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return "EdgeAnnouncement{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
