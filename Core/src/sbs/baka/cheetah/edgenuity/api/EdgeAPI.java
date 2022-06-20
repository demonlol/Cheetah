package sbs.baka.cheetah.edgenuity.api;

import org.json.*;
import org.jsoup.*;
import sbs.baka.cheetah.edgenuity.api.err.*;
import sbs.baka.cheetah.edgenuity.api.obj.*;
import sbs.baka.cheetah.edgenuity.api.requests.*;

import java.io.*;
import java.util.*;

public class EdgeAPI {

    private String user, pass;

    private Map<String, String> cookies;
    private Object passCriteriaResults;
    private String token, dest;
    private int code, lockoutTime;

    //Cookies
    private String userId;
    private int realm;
    private String role;
    private String edgeToken;
    private String sessionId;

    /*
custom_current_activity_url	        http://r14.core.learn.edgenuity.com/lmsapi/req/navigation/GetCurrentActivity
custom_start_activity_url	        http://r14.core.learn.edgenuity.com/lmsapi/req/navigation/StartActivity
custom_end_activity_lookup_url	    http://r14.core.learn.edgenuity.com/lmsapi/req/navigation/getendactivityinfo
custom_grade_bar_info_url	        http://r14.core.learn.edgenuity.com/lmsapi/req/graderesult/GetGradeBarInfo
lis_outcome_service_url         	http://r14.core.learn.edgenuity.com/lis/ResultManager.svc/ReplaceResult/
custom_teacher_comment_url	        http://r14.core.learn.edgenuity.com/lmsapi/req/TeacherComment/GetTeacherComment/
custom_activity_lookup_url	        http://r14.core.learn.edgenuity.com/lmsapi/req/navigation/getnextactivity
custom_previous_activity_lookup_url	http://r14.core.learn.edgenuity.com/lmsapi/req/navigation/getpreviousactivity
custom_load_activity_attempt_url	http://r14.core.learn.edgenuity.com/lmsapi/req/navigation/LoadActivityAttempt
custom_content_vars_lookup_url	    http://r14.core.learn.edgenuity.com/lmsapi/req/lti/getcontentvariables
     */

    public EdgeAPI(String user, String pass) throws EdgeAuthenticationException {
        this.user = user;
        this.pass = pass;

        fetchEdgeAuthenticationData();

        System.out.println(getUser());
    }

    //{"Code":1,"Token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiJhY2U3ZDdkZi0yMjgxLTQ4ZmItOWU5ZC1lODcwM2Q4MzBjYmIiLCJ1c2VybmFtZSI6ImFzaGZvbGtlIiwicm9sZSI6IiIsInRva2VuZXhwaXJlcyI6IjUvMTkvMjAyMiA1OjMwOjM1IFBNIiwiUmVhbG0iOiIxNCIsImZvcmNlU1NMIjoiVHJ1ZSIsIkxvZ291dFVybCI6Imh0dHBzOi8vYXV0aC5lZGdlbnVpdHkuY29tL0xvZ2luIiwicGVybWlzc2lvbnMiOiIyMjUsMCwwLDAsMCwwLDAsMCwwIiwiYXV0aHBlcm1pc3Npb25zIjoiMSwwIiwibmJmIjoxNjUyOTU5ODM2LCJleHAiOjE2NTI5ODE0MzUsImlhdCI6MTY1Mjk1OTgzNiwiaXNzIjoiRWRnZW51aXR5LmNvbSIsImF1ZCI6Imh0dHA6Ly93d3cuZWRnZW51aXR5LmNvbSJ9.23Ufj3Ae7goSeZUrmPJT9ooEzfEqwT8GkqZ9z3R-EPY","Destination":"https://r14.core.learn.edgenuity.com/Student/Authentication/Login/?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiJhY2U3ZDdkZi0yMjgxLTQ4ZmItOWU5ZC1lODcwM2Q4MzBjYmIiLCJ1c2VybmFtZSI6ImFzaGZvbGtlIiwicm9sZSI6IiIsInRva2VuZXhwaXJlcyI6IjUvMTkvMjAyMiA1OjMwOjM1IFBNIiwiUmVhbG0iOiIxNCIsImZvcmNlU1NMIjoiVHJ1ZSIsIkxvZ291dFVybCI6Imh0dHBzOi8vYXV0aC5lZGdlbnVpdHkuY29tL0xvZ2luIiwicGVybWlzc2lvbnMiOiIyMjUsMCwwLDAsMCwwLDAsMCwwIiwiYXV0aHBlcm1pc3Npb25zIjoiMSwwIiwibmJmIjoxNjUyOTU5ODM2LCJleHAiOjE2NTI5ODE0MzUsImlhdCI6MTY1Mjk1OTgzNiwiaXNzIjoiRWRnZW51aXR5LmNvbSIsImF1ZCI6Imh0dHA6Ly93d3cuZWRnZW51aXR5LmNvbSJ9.23Ufj3Ae7goSeZUrmPJT9ooEzfEqwT8GkqZ9z3R-EPY","LockoutTime":0,"PasswordCriteriaCheckResults":null}
    private void fetchEdgeAuthenticationData() {
        try {
            Connection.Response req = new Request.Builder()
                    .withMethod(Connection.Method.POST)
                    .willFollowRedirects(false)
                    .withUrl("https://auth.edgenuity.com/Login/Login/Student")
                    .withBody("{\"Username\":\"" + user + "\",\"Password\":\"" + pass + "\"}")
                    .withHeaders(getDefaultHeaders())
                    .execute();

            this.cookies = req.cookies();

            String body = req.body();
            try {
                JSONObject obj = new JSONObject(body);

                this.code = obj.getInt("Code");
                this.lockoutTime = obj.getInt("LockoutTime");
                this.passCriteriaResults = obj.get("PasswordCriteriaCheckResults");
                this.dest = obj.getString("Destination");
                this.token = obj.getString("Token");

                obj.keySet().forEach(k -> System.out.print(k + ":" + obj.get(k) + "  .  "));
            } catch (JSONException ex) {
                ex.printStackTrace();
                throw new EdgeAuthenticationException("Failed to parse response body as json: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        //Student/Authentication/Login
        try {
            Connection.Response res = new Request.Builder()
                    .withMethod(Connection.Method.GET)
                    .willFollowRedirects(true)
                    .withUrl(dest)
                    .withHeaders(getDefaultHeaders())
                    .withCookies(cookies)
                    .execute();

            this.cookies = res.cookies();

            if(cookies.containsKey("EdgeToken"))
                this.edgeToken = cookies.get("EdgeToken");
            if(cookies.containsKey("ASP.NET_SessionId")) this.sessionId = cookies.get("ASP.NET_SessionId");
            if(cookies.containsKey("TokenData")) {
                JSONObject tokenData = new JSONObject(cookies.get("TokenData"));
                if (tokenData.has("UserId")) this.userId = tokenData.getString("UserId");
                if (tokenData.has("Realm")) this.realm = tokenData.getInt("Realm");
                if (tokenData.has("Role")) this.role = tokenData.getString("Role");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EdgeActivityLaunch getActivityLaunch(EdgeEnrollment edgeEnrollment) {
        try {
            Connection.Response res = new Request.Builder()
                    .withMethod(Connection.Method.GET)
                    .withUrl("https://r14.core.learn.edgenuity.com/lmsapi/sle/api/enrollments/" + edgeEnrollment.getUuid() + "/activitylaunch")
                    .withHeaders(getDefaultHeaders())
                    .withCookies(cookies)
                    .execute();

            this.cookies = res.cookies();

            return new EdgeActivityLaunch(new JSONObject(res.body()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EdgeActivity getCurrentActivity() {
        try {
            EdgeUser edgeUser = getUser();

            StringBuilder params = new StringBuilder();
            params.append(
                    "UserID=" + edgeUser.getUserId() +
                    "&StudentBuildID=e8ad2e13-ced6-4689-9c09-cf676c6f8fce" +
                    "&ActivityOrder=0c3dd7d6-f183-ea11-aa64-a654e26711ca" +
                            "&IsSSLimited=False" +
                            "&AllowPretestsAndQuizzes=False");
            Connection.Response res = new Request.Builder()
                    .withMethod(Connection.Method.GET)
                    .withUrl("https://r14.core.learn.edgenuity.com/lmsapi/req/navigation/GetCurrentActivity?" + params)
                    .withHeaders(getDefaultHeaders())
                    .withCookies(cookies)
                    .execute();

            this.cookies = res.cookies();

            return new EdgeActivity(new JSONObject(res.body()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public EdgeUser getUser() {
        try {
            Connection.Response res = new Request.Builder()
                    .withMethod(Connection.Method.GET)
                    .withUrl("https://r14.core.learn.edgenuity.com/lmsapi/sle/api/users/" + userId)
                    .withHeaders(getDefaultHeaders())
                    .withCookies(cookies)
                    .execute();

            this.cookies = res.cookies();

            return new EdgeUser(new JSONObject(res.body()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public EdgeAnnouncement[] getAnnouncements() {
        EdgeAnnouncement[] announcements;
        try {
            Connection.Response req = new Request.Builder()
                    .withMethod(Connection.Method.GET)
                    .withUrl("https://r14.core.learn.edgenuity.com/lmsapi/sle/api/communications/announcements/user/" + userId)
                    .withHeaders(getDefaultHeaders())
                    .withCookies(cookies)
                    .execute();

            this.cookies = req.cookies();

            JSONArray jarray = new JSONArray(req.body());
            announcements = new EdgeAnnouncement[jarray.length()];
            for (int i = 0; i < jarray.toList().size(); i++) {
                announcements[i] = new EdgeAnnouncement(jarray.getJSONObject(i));
                System.out.println(announcements[i]);
            }
            return announcements;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public EdgeEnrollment[] getEnrollments() {
        EdgeEnrollment[] enrollments;
        try {
            Connection.Response req = new Request.Builder()
                    .withMethod(Connection.Method.GET)
                    .withUrl("https://r14.core.learn.edgenuity.com/lmsapi/sle/api/enrollments/user/" + userId)
                    .withHeaders(getDefaultHeaders())
                    .withCookies(cookies)
                    .execute();

            this.cookies = req.cookies();

            String body = req.body();
            JSONArray jarray = new JSONArray(body);
            enrollments = new EdgeEnrollment[jarray.length()];
            for (int i = 0; i < jarray.toList().size(); i++) {
                enrollments[i] = new EdgeEnrollment(jarray.getJSONObject(i));
                System.out.println(enrollments[i]);
            }
            return enrollments;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public EdgeInstructor[] getInstructors() {
        EdgeInstructor[] instructors;
        try {
            Connection.Response req = new Request.Builder()
                    .withMethod(Connection.Method.GET)
                    .withUrl("https://r14.core.learn.edgenuity.com/lmsapi/sle/api/communications/emails/student/recipients")
//                    .withUrl("https://r14.core.learn.edgenuity.com/lmsapi/sle/api/enrollments/user/ace7d7df-2281-48fb-9e9d-e8703d830cbb")
                    .withHeaders(getDefaultHeaders())
                    .withCookies(cookies)
                    .execute();

            this.cookies = req.cookies();

            String body = req.body();
            JSONArray jarray = new JSONArray(body);
            instructors = new EdgeInstructor[jarray.length()];
            for (int i = 0; i < jarray.toList().size(); i++) {
                JSONObject obj = jarray.getJSONObject(i);
                String name = obj.getString("name"), id = obj.getString("id");
                instructors[i] = new EdgeInstructor(name, id);
                System.out.println(name + " (" + id + ")");
            }
            return instructors;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> getDefaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        {
            headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.47");
            headers.put("content-type", "application/json");
        }
        return headers;
    }
}