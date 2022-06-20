package sbs.baka.cheetah.edgenuity.api.obj;

import org.json.*;

public class EdgeActivityLaunch {

    private boolean powerspeak, sophia, accessibleNextActivity;
    private String userId, launchUrl;

    public EdgeActivityLaunch(JSONObject obj) {
        this.powerspeak = obj.getBoolean("isPowerspeak");
        this.sophia = obj.getBoolean("isSophia");
        this.accessibleNextActivity = obj.getBoolean("hasAccessibleNextActivity");
        this.userId = obj.getString("userId");
        this.launchUrl = obj.getString("launchURL");
    }

    public boolean isSophia() {
        return sophia;
    }

    public boolean isAccessibleNextActivity() {
        return accessibleNextActivity;
    }

    public String getUserId() {
        return userId;
    }

    public String getLaunchUrl() {
        return launchUrl;
    }

    public boolean isPowerspeak() {
        return powerspeak;
    }
    public String getStudentBuildID() {
        return getLaunchUrl().substring(getLaunchUrl().lastIndexOf("StudentBuildID="), getLaunchUrl().lastIndexOf('&'));
    }
    public String getActivityID() {
        return getLaunchUrl().substring(getLaunchUrl().lastIndexOf("activityID="), getLaunchUrl().lastIndexOf('\"'));
    }
}