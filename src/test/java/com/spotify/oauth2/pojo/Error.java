package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
	public class Error {

	@JsonProperty("status")
	private Integer status;
	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	public Integer getStatus() {
	return status;
	}

	@JsonProperty("status")
	public void setStatus(Integer status) {
	this.status = status;
	}

	@JsonProperty("message")
	public String getMessage() {
	return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
	this.message = message;
	}

	}

