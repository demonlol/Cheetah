package sbs.baka.cheetah.api.genius.obj;

public class Artist {

    private String type,
                   apiPath,
                   headerImageUrl,
                   id,
                   imageUrl,
                   name,
                   slug,
                   geniusUrl;
    private String iq; //nullable
    private boolean
    indexCharacter,
    memeVerified,
    verified,
    featured;
}

/*
					"featured_artists": [{
						"_type": "artist",
						"api_path": "/artists/457273",
						"header_image_url": "https://images.genius.com/bf89a75b5f8aebaec2e6269fa0dc0493.1000x563x1.jpg",
						"id": 457273,
						"image_url": "https://images.genius.com/9e2e3d235b819a1dd97ca2eb6b0eef1e.900x900x1.jpg",
						"index_character": "d",
						"is_meme_verified": false,
						"is_verified": false,
						"name": "Dima lancaster",
						"slug": "Dima-lancaster",
						"url": "https://genius.com/artists/Dima-lancaster"
					}],
					"primary_artist": {
						"_type": "artist",
						"api_path": "/artists/634820",
						"header_image_url": "https://images.genius.com/9fb9b0a6f3c590d0b476d61067da3742.1000x563x1.jpg",
						"id": 634820,
						"image_url": "https://images.genius.com/7e2d9de389c92911207be4c34d710c20.400x400x1.jpg",
						"index_character": "a",
						"is_meme_verified": false,
						"is_verified": true,
						"name": "AmaLee",
						"slug": "Amalee",
						"url": "https://genius.com/artists/Amalee",
						"iq": 3081
					}
 */
