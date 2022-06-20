package sbs.baka.cheetah.edgenuity.api.obj;

import org.json.*;



public class EdgeActivity {

    private EdgeNavigation edgeNavigation;

    public EdgeActivity(JSONObject object) {
         this.edgeNavigation = new EdgeNavigation(object.getJSONObject("Navigation"));
    }

    public EdgeNavigation getEdgeNavigation() {
        return edgeNavigation;
    }

    public static class EdgeNavigation {
        private String hash, lessonName, activityName, activityStatus, activityOrder, courseId, attemptId, glossaryId, toolsId, notesId, transcriptId, learningObjKey, lessonKey, resourceLinkId, progress;
        private EdgeActivity previous, next;
        private boolean restrictedActivity, navigationLocked;

        public EdgeNavigation(JSONObject obj) {
            this.lessonName = obj.getString("LessonName");
            this.activityName = obj.getString("ActivityName");
            this.activityOrder = obj.getString("ActivityOrder");

            this.hash = obj.getString("Hash");
            this.activityStatus = obj.getString("ActivityStatus");
            this.courseId = obj.getString("CourseID");
            this.attemptId = obj.getString("AttemptID");
            this.glossaryId = obj.getString("GlossaryID");
            this.toolsId = obj.getString("ToolsID");
            this.notesId = obj.getString("NotesID");
            this.transcriptId = obj.getString("TranscriptID");
            this.learningObjKey = obj.getString("LearningObjectKey");
            this.lessonKey = obj.getString("LessonKey");
            this.resourceLinkId = obj.getString("ResourceLinkID");
            this.progress = obj.getString("Progress");
            this.restrictedActivity = obj.getBoolean("IsRestrictedActivity");
            this.navigationLocked = obj.getBoolean("NavigationLocked");
//            this.previous =
//            this.next =
        }
    }

    /*
    {
	"Navigation": {
		"Hash": null,
		"LessonName": "Unit Test",
		"ActivityName": "Unit Test",
		"ActivityStatus": "Active",
		"ActivityOrder": "0c3dd7d6-f183-ea11-aa64-a654e26711ca",
		"CourseID": null,
		"AttemptID": null,
		"GlossaryID": "b6f9627e-a30b-4d86-966d-18ed84d9a47b",
		"ToolsID": "155070547",
		"NotesID": "b6f9627e-a30b-4d86-966d-18ed84d9a47b",
		"TranscriptID": "6f042362-6a62-6365-7473-000000000000",
		"LearningObjectKey": "6f042362-6a62-6365-7473-000000000000",
		"LessonKey": "b6f9627e-a30b-4d86-966d-18ed84d9a47b",
		"ResourceLinkID": null,
		"Progress": 14.45,
		"PrevActivity": {
			"LessonName": "Unit Test",
			"ActivityName": "Unit Test Review",
			"ActivityOrder": "0a3dd7d6-f183-ea11-aa64-a654e26711ca"
		},
		"NextActivity": {
			"LessonName": "Unit Test",
			"ActivityName": "Unit Test",
			"ActivityOrder": "0c3dd7d6-f183-ea11-aa64-a654e26711ca"
		},
		"Warning": null,
		"ShowOverlay": true,
		"LookupLink": null,
		"UserID": null,
		"ContextID": null,
		"CEContextID": null,
		"Role": null,
		"ConsumerKey": null,
		"NavigationLocked": false,
		"EssayType": null,
		"ObjectType": "AT",
		"IsRestrictedActivity": true
	},
	"AssessmentOverlay": null,
	"Header": {
		"CorrelationID": "18b909bb-d3e8-4ee2-9abe-2a830eda5468",
		"TransactionDateTime": "2022-05-04T09:17:16.5698933Z"
	}
}
     */

}
