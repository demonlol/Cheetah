package sbs.baka.cheetah.edgenuity.api.obj;

import org.json.*;

import java.util.*;

public class EdgeUser {

    private int groupId;
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean impersonating;
    private String userName;
    private int districtId;
    private long userId;
    private String districtName;
    private int grade;
    private boolean collabCorner;
    private boolean announcementsEnabled;
    private boolean aboutUsEnabled;
    private boolean role;
    private boolean homeButtonDisabled;
    private boolean eNotesEnabled;
    private boolean helpCenterEnabled;
    private boolean selfEnrollmentEnabled;

    public EdgeUser(JSONObject obj) {
        this.groupId = obj.getInt("groupId");
        this.grade = obj.getInt("grade");
        this.email = obj.getString("email");
        this.userId = obj.getLong("userId");
        this.id = UUID.fromString(obj.getString("id"));
        this.collabCorner = obj.getBoolean("collabCorner");
        this.groupId = obj.getInt("groupId");
        this.firstName = obj.getString("firstName");
        this.lastName = obj.getString("lastName");
        this.impersonating = obj.getBoolean("isImpersonating");
        this.userName = obj.getString("userName");
        this.districtId = obj.getInt("districtId");
        this.districtName = obj.getString("districtName");
        this.announcementsEnabled = obj.getBoolean("announcementsEnabled");
        this.aboutUsEnabled = obj.getBoolean("aboutUsEnabled");
        this.role = obj.getBoolean("role");
        this.homeButtonDisabled = obj.getBoolean("homeButtonDisabled");
        this.eNotesEnabled = obj.getBoolean("eNotesEnabled");
        this.helpCenterEnabled = obj.getBoolean("helpCenterEnabled");
        this.selfEnrollmentEnabled = obj.getBoolean("selfEnrollmentEnabled");
    }

    public int getGroupId() {
        return groupId;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isImpersonating() {
        return impersonating;
    }

    public String getUserName() {
        return userName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public long getUserId() {
        return userId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public int getGrade() {
        return grade;
    }

    public boolean isCollabCorner() {
        return collabCorner;
    }

    public boolean isAnnouncementsEnabled() {
        return announcementsEnabled;
    }

    public boolean isAboutUsEnabled() {
        return aboutUsEnabled;
    }

    public boolean isRole() {
        return role;
    }

    public boolean isHomeButtonDisabled() {
        return homeButtonDisabled;
    }

    public boolean iseNotesEnabled() {
        return eNotesEnabled;
    }

    public boolean isHelpCenterEnabled() {
        return helpCenterEnabled;
    }

    public boolean isSelfEnrollmentEnabled() {
        return selfEnrollmentEnabled;
    }

    @Override
    public String toString() {
        return "EdgeUser{" +
                "groupId=" + groupId +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", impersonating=" + impersonating +
                ", userName='" + userName + '\'' +
                ", districtId=" + districtId +
                ", userId=" + userId +
                ", districtName='" + districtName + '\'' +
                ", grade=" + grade +
                ", collabCorner=" + collabCorner +
                ", announcementsEnabled=" + announcementsEnabled +
                ", aboutUsEnabled=" + aboutUsEnabled +
                ", role=" + role +
                ", homeButtonDisabled=" + homeButtonDisabled +
                ", eNotesEnabled=" + eNotesEnabled +
                ", helpCenterEnabled=" + helpCenterEnabled +
                ", selfEnrollmentEnabled=" + selfEnrollmentEnabled +
                '}';
    }
}
