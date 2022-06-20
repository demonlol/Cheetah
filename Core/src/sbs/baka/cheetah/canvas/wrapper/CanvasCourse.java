package sbs.baka.cheetah.canvas.wrapper;

import consulting.freiheitsgrade.patched.commons.configuration2.*;
import consulting.freiheitsgrade.patched.commons.configuration2.ex.*;
import edu.ksu.canvas.model.*;
import edu.ksu.canvas.model.Module;
import edu.ksu.canvas.model.assignment.*;
import edu.ksu.canvas.requestOptions.*;
import sbs.baka.cheetah.storage.filesys.FileSystem;

import java.io.*;
import java.io.File;
import java.lang.*;
import java.util.*;

public class CanvasCourse implements Serializable {

    /*
        Name
        Credit Hours
        sbs.baka.cheetah.Start Date, End Date
        Teachers[]
        Students[]
        Assignments[]
        Latest Activity
        Notes[noteobj]
        Grade
            Points
            TotalPoints
            GPA
            Weights
        getMissingAssignments()
        getActiveAssignments()
     */

    private static final long serialVersionUID = 420691337L;

    private CanvasController controlLib;
    private Course course;

    private List<Assignment> assignments;
    private List<CourseSettings> settings;
    private List<GradingStandard> gradingStandards;
    private List<AssignmentGroup> groups;
    private List<Section> sections;
    private List<Feature> features;
    private List<Module> modules;
    private List<Tab> tabs;
    private List<CommunicationChannel> communicationChannels;
    private List<CalendarEvent> events;

    public CanvasCourse(CanvasController controlLib, Course course) {
        this.controlLib = controlLib;
        this.course = course;
    }

    public long getCourseId() { return course.getId(); }
    public List<Assignment> getAssignments() { return (this.assignments = controlLib.getAssignmentsByCourseId(getStringId())); }
    public List<CourseSettings> getSettings() { return (this.settings = controlLib.getCourseSettings(getStringId())); }
    public List<GradingStandard> getGradingStandards() { return (this.gradingStandards = controlLib.getGradingStandard(getStringId())); }
    public List<AssignmentGroup> getGroups() { return (this.groups = controlLib.getAssignmentWeightGroups(getStringId())); }
    public List<Feature> getFeatures() { return (this.features = controlLib.getCourseFeatures(getStringId())); }
    public List<Module> getModules() { return (this.modules = controlLib.getModules(new ListModulesOptions(course.getId()))); }
    public List<Tab> getTabs() { return (this.tabs = controlLib.getTabs(getStringId())); }
    public List<CommunicationChannel> getCommunicationChannels() { return (this.communicationChannels = controlLib.getCommunicationChannels()); }
    public List<CalendarEvent> getEvents() { return (this.events = controlLib.getCalendar(new ListCalendarEventsOptions())); }
    public List<CalendarEvent> getEvents(ListCalendarEventsOptions opts) { return (this.events = controlLib.getCalendar(opts)); }

    @Deprecated
    public List<Section> getSections() { return sections; }

    public long getId() { return this.course.getId(); }
    public String getStringId() { return String.valueOf(this.getId()); }

    public long getEnrollmentTermId() {
        return course.getEnrollmentTermId();
    }
    public Long getAccountId() {
        return course.getAccountId();
    }
    public String getCourseCode() {
        return course.getCourseCode();
    }
    public String getDefaultView() {
        return course.getDefaultView();
    }
    public String getName() {
        return course.getName();
    }
    public Date getCreatedAt() {
        return course.getCreatedAt();
    }
    public Date getStartAt() {
        return course.getStartAt();
    }
    public Date getEndAt() {
        return course.getEndAt();
    }
    public Boolean getPublicSyllabus() {
        return course.getPublicSyllabus();
    }
    public Long getStorageQuotaMb() {
        return course.getStorageQuotaMb();
    }
    public Boolean getHideFinalGrades() {
        return course.getHideFinalGrades();
    }
    public Boolean getApplyAssignmentGroupWeights() {
        return course.getApplyAssignmentGroupWeights();
    }
    public String getSisCourseId() {
        return course.getSisCourseId();
    }
    public String getIntegrationId() {
        return course.getIntegrationId();
    }
    public String getWorkflowState() {
        return course.getWorkflowState();
    }
    public Long getTotalStudents() {
        return course.getTotalStudents();
    }
    public EnrollmentTerm getEnrollmentTerm() { return course.getEnrollmentTerm(); }
    public List<Enrollment> getEnrollments() {
        return course.getEnrollments();
    }
    public Boolean getRestrictEnrollmentsToCourseDates() {
        return course.getRestrictEnrollmentsToCourseDates();
    }
    public Long getGradingStandardId() {
        return course.getGradingStandardId();
    }
    public String getSubaccountName() {
        return course.getSubaccountName();
    }
    public List<UserDisplay> getTeachers() {
        return course.getTeachers();
    }
    public String getLicense() {
        return course.getLicense();
    }
    public Boolean getIsPublic() {
        return course.getIsPublic();
    }
    public Boolean getIsPublicToAuthUsers() {
        return course.getIsPublicToAuthUsers();
    }
    public Boolean getPublicSyllabusToAuth() {
        return course.getPublicSyllabusToAuth();
    }
    public Boolean getAllowStudentWikiEdits() {
        return course.getAllowStudentWikiEdits();
    }
    public Boolean getAllowWikiComments() {
        return course.getAllowWikiComments();
    }
    public Boolean getAllowStudentForumAttachments() {
        return course.getAllowStudentForumAttachments();
    }
    public Boolean getOpenEnrollment() {
        return course.getOpenEnrollment();
    }
    public Boolean getSelfEnrollment() {
        return course.getSelfEnrollment();
    }
    public String getTermId() {
        return course.getTermId();
    }
    public String getTimeZone() {
        return course.getTimeZone();
    }
    public Boolean getOffer() {
        return course.getOffer();
    }
    public Boolean getEnrollMe() {
        return course.getEnrollMe();
    }
    public String getSyllabusBody() {
        return course.getSyllabusBody();
    }
    public String getCourseFormat() {
        return course.getCourseFormat();
    }
    public Boolean getEnableSisReactivation() {
        return course.getEnableSisReactivation();
    }
    public Account getAccount() {
        return course.getAccount();
    }
    public Long getImageId() {
        return course.getImageId();
    }
    public String getImageUrl() {
        return course.getImageUrl();
    }
    public String getImageDownloadUrl() {
        return course.getImageDownloadUrl();
    }
    public Boolean getBlueprint() {
        return course.getBlueprint();
    }
    public Boolean getConcluded() {
        return course.getConcluded();
    }
    public String getPublicDescription() {
        return course.getPublicDescription();
    }
    public Course.CourseEvent getEvent() {
        return course.getEvent();
    }
    
}