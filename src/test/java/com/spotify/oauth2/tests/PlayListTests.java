package com.spotify.oauth2.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlayListApi;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.PlayList;
import com.spotify.oauth2.utils.FakerUtils;

import io.restassured.response.Response;

public class PlayListTests {
	
	@Test
	public void shouldBeAbleToCreatePlaylist() {
		
		PlayList requestPlayList = PlayList.builder()
				.name(FakerUtils.getName())
				.description(FakerUtils.getDescription())
				._public(false).build();
		
		Response response = PlayListApi.post(requestPlayList);
			assertThat(response.statusCode(), equalTo(StatusCode.CODE_201.getCode()));
		
		PlayList responsePlayList = response.as(PlayList.class);
		
		assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName())); 
		assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription())); 
		assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public())); 
		System.out.println("Hello");
	}
	
	@Test
	public void shouldBeAbleToGetAPlaylist() {
		
		PlayList requestPlayList = PlayList.builder()
				.name("Updated Playlist")
				.description("Updated playlist description")
				._public(false).build();
		
		Response response = PlayListApi.get("7tPLvQUT9PKatKlwXHAUjt");
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
		
		PlayList responsePlayList = response.as(PlayList.class);
		
		assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName())); 
		assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription())); 
		assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public())); 
	}
	
	@Test
	public void shouldBeAbleToUpdateAPlaylist() {
		
		PlayList requestPlayList = PlayList.builder()
				.name(FakerUtils.getName())
				.description(FakerUtils.getDescription())
				._public(false).build();
		
		Response response = PlayListApi.put(requestPlayList, "0FJX9i4r2ytone0IUQb40O");
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
	}
	
	@Test
	public void shouldNotBeAbleToCreatePlaylistWithoutName() {
		
		PlayList requestPlayList = PlayList.builder()
				.name("")
				.description(FakerUtils.getDescription())
				._public(false).build();
		
		Response response = PlayListApi.post(requestPlayList);
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_400.getCode()));
	
	ErrorRoot responseErrorRoot = response.as(ErrorRoot.class);
		
		assertThat(responseErrorRoot.getError().getStatus(), equalTo(StatusCode.CODE_400.getCode())); 
		assertThat(responseErrorRoot.getError().getMessage(), equalTo(StatusCode.CODE_400.getMsg())); 
	}
	
	@Test
	public void shouldNotBeAbleToCreatePlaylistWithExpiredToken() {
		PlayList requestPlayList = PlayList.builder()
				.name(FakerUtils.getName())
				.description(FakerUtils.getDescription())
				._public(false).build();
		
		Response response = PlayListApi.post(requestPlayList, "12345");
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_401.getCode()));
	
	ErrorRoot responseErrorRoot = response.as(ErrorRoot.class);
			
		assertThat(responseErrorRoot.getError().getStatus(), equalTo(StatusCode.CODE_401.getCode())); 
		assertThat(responseErrorRoot.getError().getMessage(), equalTo(StatusCode.CODE_401.getMsg())); 
	}

}
