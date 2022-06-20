package sbs.baka.cheetah.audio.pimp;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.extensions.java6.auth.oauth2.*;
import com.google.api.client.extensions.jetty.auth.oauth2.*;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.json.*;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.*;
import com.google.api.client.json.*;
import com.google.api.client.json.jackson2.*;
import com.google.api.client.util.store.*;
import com.google.api.services.youtube.*;
import com.google.api.services.youtube.model.*;
import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.event.*;
import java.io.*;
import java.util.*;

import static sbs.baka.cheetah.audio.pimp.PIMPFeature.Search.*;

//Personal Intricate Music Player or something

public class PIMPFeature extends WindowFeature {

    private static final String PROPERTIES_FILENAME = "youtube.properties";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

    private static YouTube youtube;

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }

    public class Auth {

        public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        public static final JsonFactory JSON_FACTORY = new JacksonFactory();
        private static final String CREDENTIALS_DIRECTORY = ".oauth-credentials";

        public static Credential authorize(List<String> scopes, String credentialDatastore) throws IOException {
            // Load client secrets.
            Reader clientSecretReader = new InputStreamReader(Auth.class.getResourceAsStream("/client_secrets.json"));
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, clientSecretReader);

            // Checks that the defaults have been replaced (Default = "Enter X here").
            if (clientSecrets.getDetails().getClientId().startsWith("Enter") || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
                System.out.println("Enter Client ID and Secret from https://console.developers.google.com/project/_/apiui/credential " + "into src/main/resources/client_secrets.json");
                System.exit(1);
            }

            // This creates the credentials datastore at ~/.oauth-credentials/${credentialDatastore}
            FileDataStoreFactory fileDataStoreFactory = new FileDataStoreFactory(new File(System.getProperty("user.home") + "/" + CREDENTIALS_DIRECTORY));
            DataStore<StoredCredential> datastore = fileDataStoreFactory.getDataStore(credentialDatastore);

            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes).setCredentialDataStore(datastore).build();

            // Build the local server and bind it to port 8080
            LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8080).build();

            // Authorize.
            return new AuthorizationCodeInstalledApp(flow, localReceiver).authorize("user");
        }
    }

    public class Search {

        static String getInputQuery() throws IOException {
            String inputQuery = "";

            System.out.print("Please enter a search term: ");
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            inputQuery = bReader.readLine();

            if (inputQuery.length() < 1) {
                // Use the string "YouTube Developers Live" as a default.
                inputQuery = "YouTube Developers Live";
            }
            return inputQuery;
        }

        static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

            System.out.println("\n=============================================================");
            System.out.println(
                    "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
            System.out.println("=============================================================\n");

            if (!iteratorSearchResults.hasNext()) {
                System.out.println(" There aren't any results for your query.");
            }

            while (iteratorSearchResults.hasNext()) {

                SearchResult singleVideo = iteratorSearchResults.next();
                ResourceId rId = singleVideo.getId();

                // Confirm that the result represents a video. Otherwise, the
                // item will not contain a video ID.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                    System.out.println(" Video Id" + rId.getVideoId());
                    System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                    System.out.println(" Thumbnail: " + thumbnail.getUrl());
                    System.out.println("\n-------------------------------------------------------------\n");
                }
            }
        }
    }

    @Override
    public InternalFrame.Builder getInternalFrame() {
        return new InternalFrame.Builder()
                .withTitle(getName())
                .withRunnableAndInstance((frame) -> {
                    // Read the developer key from the properties file.
                    Properties properties = new Properties();
                    try {
                        InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
                        properties.load(in);

                    } catch (IOException e) {
                        System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause() + " : " + e.getMessage());
                        System.exit(1);
                    }

                    try {
                        // This object is used to make YouTube Data API requests. The last
                        // argument is required, but since we don't need anything
                        // initialized when the HttpRequest is initialized, we override
                        // the interface and provide a no-op function.
                        youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, request -> {

                        }).setApplicationName("youtube-cmdline-search-sample").build();

                        // Prompt the user to enter a query term.
                        String queryTerm = getInputQuery();

                        // Define the API request for retrieving search results.
                        YouTube.Search.List search = youtube.search().list(Collections.singletonList("id,snippet"));

                        // Set your developer key from the {{ Google Cloud Console }} for
                        // non-authenticated requests. See:
                        // {{ https://cloud.google.com/console }}
                        String apiKey = properties.getProperty("youtube.apikey");
                        search.setKey(apiKey);
                        search.setQ(queryTerm);

                        // Restrict the search results to only include videos. See:
                        // https://developers.google.com/youtube/v3/docs/search/list#type
                        search.setType(Collections.singletonList("video"));

                        // To increase efficiency, only retrieve the fields that the
                        // application uses.
                        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
                        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

                        // Call the API and print results.
                        SearchListResponse searchResponse = search.execute();
                        List<SearchResult> searchResultList = searchResponse.getItems();
                        if (searchResultList != null) {
                            prettyPrint(searchResultList.iterator(), queryTerm);
                        }
                    } catch (GoogleJsonResponseException e) {
                        System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                                + e.getDetails().getMessage());
                    } catch (IOException e) {
                        System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                })
                ;
    }

    @Override
    public String getName() {
        return "P.I.M.P (Personal Intelligent Music Player)";
    }

    @Override
    public String getDesc() {
        return "Best music player you'll ever need";
    }
}
