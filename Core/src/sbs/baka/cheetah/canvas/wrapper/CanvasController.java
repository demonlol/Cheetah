package sbs.baka.cheetah.canvas.wrapper;

import edu.ksu.canvas.*;
import edu.ksu.canvas.enums.*;
import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.interfaces.FileReader;
import edu.ksu.canvas.model.*;
import edu.ksu.canvas.model.File;
import edu.ksu.canvas.model.Module;
import edu.ksu.canvas.model.assignment.*;
import edu.ksu.canvas.oauth.*;
import edu.ksu.canvas.requestOptions.*;
import org.apache.commons.lang3.tuple.*;
import org.slf4j.*;
import sbs.baka.cheetah.canvas.cache.*;
import sbs.baka.cheetah.storage.filesys.FileSystem;
import sbs.baka.cheetah.util.*;
import sbs.baka.cheetah.util.parse.*;

import java.awt.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;

public class CanvasController {

	private final Logger log = LoggerFactory.getLogger(CanvasController.class);

	private final CanvasApiFactory api;
	private final OauthToken accessToken;
	private final String self = "self"; //userId
	private final User user;
	private final long userId;

	private CanvasCacher cacher; //if theres a cache it should be user based...............
	private java.io.File cacheRoot;
	private java.io.File coursesRoot;

	//https://canvas.instructure.com/doc/api/quiz_assignment_overrides.html - If and when assignments/quizzes due dates
	//have been overwritten.
	//Add schedule/timer to refresh
	//Add IO cache
	//Add another wrapper i.e Semester/Term, Courses w/ teacher objects (addable emails),
	//Quiz wrapper - quiz.getQuestions(5).setAnswer("") etc etc...
	//QuizQuestionReader
	//QuizQuestionWriter
	//QuizSubmissionReader
	//QuizSubmissionWriter
	//QuizReader
	//QuizSubmissionQuestionReader
	//QuizSubmissionQuestionWriter
	//QuizWriter
	//RoleReader
	//SectionReader
	//SelectiveDataReader
	//SubmissionReader
	//SubmissionWriter
	//TabReader
	//TabWriter
	//UserReader - getUser
	//UserWriter

	public CanvasController(String schoolAccountName, String accessToken) {
		this.cacher = new CanvasCacher(this);

		this.api = new CanvasApiFactory("https://" + schoolAccountName+ ".instructure.com");
		this.accessToken = new NonRefreshableOauthToken(accessToken);
		this.user = getUser();
		this.userId = user.getId();
	}

//  ------------------------------------------------------------------------------------------------------------------

	//Readers
	public User getUser() {
		try {
			List<User> canvasObjs = cacher.getCachedCanvasObjects(User.class);
			User user;
			if(canvasObjs != null && (user = canvasObjs.stream().findAny().get()) != null) {
				return user;
			} else {
				User u = rdr(UserReader.class).showUserDetails(self).get();
				ImmutablePair<Integer, Integer> pair = cacher.cachedCanvasObjects(User.class, new ArrayList<User>(Collections.singleton(u)));
				if(pair.left > 0) log.debug("Cached " + u.getClass().getSimpleName() + " " + User.class.getSimpleName() + " objects (" + (pair.right / 1024.00) + "kb).");
				return u;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Tab> getTabs(String courseId) {
		try {
			List<Tab> canvasObjs = cacher.getCachedCanvasObjects(Tab.class);
			if(canvasObjs != null && canvasObjs.size() > 0) {
				return canvasObjs;
			} else {
				List<Tab> tabs = rdr(TabReader.class).listAvailableCourseTabs(courseId, true);
				ImmutablePair<Integer, Integer> cachedObjs = cacher.cachedCanvasObjects(Tab.class, tabs);
				if(cachedObjs.left > 0) log.debug("Cached " + cachedObjs + " " + Course.class.getSimpleName() + " objects (" + (cachedObjs.right / 1024.00) + "kb).");
				return tabs;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	//2138 non cache vws 47 cache

	public List<Course> getCourses() {
		try {
			List<Course> canvasObjs = cacher.getCachedCanvasObjects(Course.class);
			if(canvasObjs != null && canvasObjs.size() > 0) {
				return canvasObjs;
			} else {
				List<Course> freshCourseList = rdr(CourseReader.class).listCurrentUserCourses(new ListCurrentUserCoursesOptions().includes(Arrays.stream(ListCurrentUserCoursesOptions.Include.values()).toList()));
				ImmutablePair<Integer, Integer> cachedObjs = cacher.cachedCanvasObjects(Course.class, freshCourseList);

				if(cachedObjs.left > 0) log.debug("Cached " + cachedObjs + " " + Course.class.getSimpleName() + " objects (" + (cachedObjs.right / 1024.00) + "kb).");
				return freshCourseList;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<Course> getCourses(ListCurrentUserCoursesOptions opts) {
		try { return rdr(CourseReader.class).listCurrentUserCourses(opts); } catch (IOException e) { e.printStackTrace(); return null; }
	}



	//SUBMISSION, ASSIGNMENT_VISIBILITY, ALL_DATES, OVERRIDES, OBSERVED_USERS;
	public List<Assignment> getAssignmentsByCourseId(String courseId, ListCourseAssignmentsOptions.Include...includes) {
		try {
			return rdr(AssignmentReader.class).listCourseAssignments(new ListCourseAssignmentsOptions(courseId).includes(includes.length > 0 ? Arrays.stream(includes).toList() : Collections.emptyList()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<AssignmentGroup> getAssignmentWeightGroups(String courseId) {
		try { return rdr(AssignmentGroupReader.class).listAssignmentGroup(new ListAssignmentGroupOptions(courseId).includes(Arrays.stream(ListAssignmentGroupOptions.Include.values()).toList())); } catch (IOException e) { e.printStackTrace(); return null; }
	}



	public Map<Assignment, AssignmentOverride> getAssignmentOverrides(String courseId) {
		Map<Assignment, AssignmentOverride> assignmentOverrides = new HashMap<>();

		for (Assignment assignment : getAssignmentsByCourseId(courseId,ListCourseAssignmentsOptions.Include.OVERRIDES)) {
			try {
				List<AssignmentOverride> overrides = rdr(AssignmentOverrideReader.class).listAssignmentOverrides(courseId, assignment.getId());
				for (AssignmentOverride override : overrides) {
					if(override.isUserOverwrittenForUserId(userId)) {
						assignmentOverrides.put(assignment, override);
					}
				}
			} catch (IOException e) { e.printStackTrace(); }

		}
		return assignmentOverrides;
	}



	public List<AuthenticationLog> getAuthenticationLog() {
		try {
			return rdr(AuthenticationLogReader.class).getAuthenticationLogForUser(self).stream().toList();
		} catch (IOException e) {
			return null;
		}
	}



	public List<CalendarEvent> getCalendar(ListCalendarEventsOptions opts) {
		if(opts == null) opts = new ListCalendarEventsOptions();

		try {
			return rdr(CalendarReader.class).listCurrentUserCalendarEvents(opts).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<CommunicationChannel> getCommunicationChannels() {
		try {
			return rdr(CommunicationChannelReader.class).getCommunicationChannelsForUser(self).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	//wtf does this do
	public List<ContentMigration> getContentMigration() {
		try {
			return rdr(ContentMigrationReader.class).getUserContentMigrations(self).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<Conversation> getConversation(GetSingleConversationOptions opts) {
		if(opts == null) {
			throw new NullPointerException("Can't retrieve conversation without valid GetSingleConversation options");
		}
		try {
			return rdr(ConversationReader.class).getSingleConversation(opts).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<CourseSettings> getCourseSettings(String courseId) {
		try {
			return rdr(CourseSettingsReader.class).getCourseSettings(courseId).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<Enrollment> getUserEnrollment (GetEnrollmentOptions opts) {
		if(opts == null) opts = new GetEnrollmentOptions(null);
		try {
			return rdr(EnrollmentReader.class).getUserEnrollments(opts).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<ExternalTool> getExternalTools(ListExternalToolsOptions opts) {
		if(opts == null) {
			throw new NullPointerException("External tool ListExternalToolsOptions cannot be null");
		}
		try {
			return rdr(ExternalToolReader.class).listExternalToolsInCourse(opts).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public Optional<FeatureFlag> getUserFeatureFlag(String featureFlag) {
		try {
			return rdr(FeatureFlagReader.class).getUserFeatureFlag(self, featureFlag);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<Feature> getUserFeatures() {
		try {
			return rdr(FeatureReader.class).getUserFeatures(self).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<Feature> getCourseFeatures(String courseId) {
		try {
			return rdr(FeatureReader.class).getCourseFeatures(courseId).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public Optional<File> getFile(String url) {
		try {
			return rdr(FileReader.class).getFile(url);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<GradingStandard> getGradingStandard (String courseId) {
		try {
			return rdr(GradingStandardReader.class).listGradingStandardsInCourse(courseId).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<Login> getLogins() {
		try {
			return rdr(LoginReader.class).getLoginForUser(self).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	//Migration = something to do with file uploads
	// public List<MigrationIssue> getUserMigrationIssues() {
	// 	return rdr(MigrationIssueReader.class).getUserMigrationIssues(self, )
	// }
	public List<Module> getModules(ListModulesOptions opts) {
		try {
			return rdr(ModuleReader.class).getModulesInCourse(opts).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public List<Page> getPages(String courseId) {
		try {
			return rdr(PageReader.class).listPagesInCourse(courseId).stream().toList();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public Optional<Progress> getProgress(String urlOrProgressId) {
		try {
			return rdr(ProgressReader.class).getProgress(urlOrProgressId);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

//  ------------------------------------------------------------------------------------------------------------------

	//Writers

//  ------------------------------------------------------------------------------------------------------------------

	public <T extends CanvasReader> T rdr(Class<T> clazz) {
		if (accessToken == null) throw new NullPointerException("The Canvas access token is null.");
		return api.getReader(clazz, accessToken);
	}
	public <T extends CanvasWriter> T wtr(Class<T> clazz) {
		if (accessToken == null) throw new NullPointerException("The Canvas access token is null.");
		return api.getWriter(clazz, accessToken);
	}
	public CanvasApiFactory getApi () { return api; }
	public OauthToken getAccessToken () { return accessToken; }

}
