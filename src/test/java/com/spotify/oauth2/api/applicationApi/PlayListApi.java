package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import static com.spotify.oauth2.api.Route.*;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.PlayList;
import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.response.Response;

public class PlayListApi {
	
	public static Response post(PlayList requestPlayList) {
		return RestResource.post(USERS +"/"+ ConfigLoader.getInstance().getUserId() + PLAYLISTS, TokenManager.getToken(), requestPlayList);
	}
	
	public static Response post(PlayList requestPlayList, String token) {
		return RestResource.post(USERS +"/"+ ConfigLoader.getInstance().getUserId() +PLAYLISTS, token, requestPlayList);
	}
	
	public static Response get(String playListId) {
		return RestResource.get(PLAYLISTS + "/" + playListId, TokenManager.getToken());
	}
	
	public static Response put(PlayList requestPlayList, String playListId) {
		return RestResource.put(requestPlayList, TokenManager.getToken(), PLAYLISTS + "/" + playListId);
	}

}
