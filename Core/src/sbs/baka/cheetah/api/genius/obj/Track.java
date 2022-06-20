package sbs.baka.cheetah.api.genius.obj;

public class Track {

    private TrackType trackType;
    private int annotationCount;
    private String apiPath, artist_names, fullTitle, headerImageThumbnailUrl, headerImageUrl, id, instrumental, lyrics_owner_id, lyrics_state, lyricsUpdatedAt, path, pyongs_count, releaseDateForDisplay,
    songArtImageThumbnailUrl, songArtImageUrl;
    private int unreviewedAnnotations;
    private boolean hot;
    private int pageViews;
    private String title, titleWithFeatures;
    private long updatedAt; //ms
    private String geniusUrl;

}

/*
				"highlights": [],
				"index": "song",
				"type": "song",
				"result": {
					"_type": "song",
					"annotation_count": 2,
					"api_path": "/songs/3141649",
					"artist_names": "AmaLee (Ft. Dima lancaster)",
					"full_title": "Your Lie in April - Medley by AmaLee (Ft. Dima lancaster)",
					"header_image_thumbnail_url": "https://images.genius.com/7fdb7025fc42adc2d94f15e799756d5f.300x169x1.jpg",
					"header_image_url": "https://images.genius.com/7fdb7025fc42adc2d94f15e799756d5f.1000x563x1.jpg",
					"id": 3141649,
					"instrumental": false,
					"lyrics_owner_id": 4106131,
					"lyrics_state": "complete",
					"lyrics_updated_at": 1560582257,
					"path": "/Amalee-your-lie-in-april-medley-lyrics",
					"pyongs_count": 3,
					"release_date_for_display": "October 5, 2015",
					"song_art_image_thumbnail_url": "https://images.genius.com/88925d35bf846aa7066ac5e415dc425e.300x300x1.jpg",
					"song_art_image_url": "https://images.genius.com/88925d35bf846aa7066ac5e415dc425e.1000x1000x1.jpg",
					"stats": {
						"unreviewed_annotations": 1,
						"hot": false,
						"pageviews": 14124
					},
					"title": "Your Lie in April - Medley",
					"title_with_featured": "Your Lie in April - Medley (Ft. Dima lancaster)",
					"updated_by_human_at": 1560582257,
					"url": "https://genius.com/Amalee-your-lie-in-april-medley-lyrics",
				}
 */
