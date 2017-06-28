package com.wowa.sorter.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDetailsDTO
{
	@JsonProperty("error")
	public final String message;

	@JsonProperty("request url")
	public final String url;

	public ErrorDetailsDTO(String message, String url)
	{
		this.message = message;
		this.url = url;
	}
}
