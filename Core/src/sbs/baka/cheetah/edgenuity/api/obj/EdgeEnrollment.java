package sbs.baka.cheetah.edgenuity.api.obj;

import org.json.*;

import java.util.*;

public class EdgeEnrollment {

    private UUID uuid;
    private String name;
    private String startDate, targetDate;
    private EnrollmentProgress progress;
    private int status;
    private int type;
    private String subject;
    private UUID masterCourseId;
    private String seriesNames;
    private boolean isSophia;

    public EdgeEnrollment(JSONObject obj) {
        this.uuid = UUID.fromString(obj.getString("id"));
        this.name = obj.getString("name");
        this.startDate = obj.getString("startDate");
        this.targetDate = obj.getString("targetDate");
        this.status = obj.getInt("status");
        this.type = obj.getInt("type");
        this.subject = obj.getString("subject");
        this.masterCourseId = UUID.fromString(obj.getString("masterCourseId"));
        this.progress = new EnrollmentProgress((obj.getJSONObject("progress")));
//        this.seriesNames = obj.getJSO //	"seriesNames": ["3000"],
        this.isSophia = obj.getBoolean("isSophia");
    }

    public static class EnrollmentProgress {

        private UUID courseId;
        private String date;
        private double percentComplete, percentCompleteCount, overallGrade, relativeGrade, percentExpected, actualGrade;

        private EnrollmentProgress() {}

        public EnrollmentProgress(JSONObject progressObj) {
            this.courseId = UUID.fromString(progressObj.getString("courseID"));
            this.date = progressObj.getString("date");
            this.percentComplete = progressObj.getDouble("percentComplete");
            this.percentCompleteCount = progressObj.getDouble("percentCompleteCount");
            this.overallGrade = progressObj.getDouble("overallGrade");
            this.relativeGrade = progressObj.getDouble("relativeGrade");
            this.percentExpected = progressObj.getDouble("percentExpected");
            this.actualGrade = progressObj.getDouble("actualGrade");
        }

        public UUID getCourseId() { return courseId; }
        public String getDate() { return date; }
        public double getPercentComplete() { return percentComplete; }
        public double getPercentCompleteCount() { return percentCompleteCount; }
        public double getOverallGrade() { return overallGrade; }
        public double getRelativeGrade() { return relativeGrade; }
        public double getPercentExpected() { return percentExpected; }
        public double getActualGrade() { return actualGrade; }

        @Override
        public String toString() {
            return "EnrollmentProgress{" +
                    "courseId=" + courseId +
                    ", date='" + date + '\'' +
                    ", percentComplete=" + percentComplete +
                    ", percentCompleteCount=" + percentCompleteCount +
                    ", overallGrade=" + overallGrade +
                    ", relativeGrade=" + relativeGrade +
                    ", percentExpected=" + percentExpected +
                    ", actualGrade=" + actualGrade +
                    '}';
        }

    }

    public UUID getUuid() { return uuid; }
    public String getName() { return name; }
    public String getStartDate() { return startDate; }
    public String getTargetDate() { return targetDate; }
    public EnrollmentProgress getProgress() { return progress; }
    public int getStatus() { return status; }
    public int getType() { return type; }
    public String getSubject() { return subject; }
    public UUID getMasterCourseId() { return masterCourseId; }
    public String getSeriesNames() { return seriesNames; }
    public boolean isSophia() { return isSophia; }

    @Override
    public String toString() {
        return "EdgeEnrollment{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", targetDate='" + targetDate + '\'' +
                ", progress=" + progress +
                ", status=" + status +
                ", type=" + type +
                ", subject='" + subject + '\'' +
                ", masterCourseId=" + masterCourseId +
                ", seriesNames='" + seriesNames + '\'' +
                ", isSophia=" + isSophia +
                '}';
    }
}
