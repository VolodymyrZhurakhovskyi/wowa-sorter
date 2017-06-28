package com.wowa.sorter.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemsListDTO
{
	private List<Integer> items;

	public ItemsListDTO()
	{
	}

	public ItemsListDTO(final List<Integer> items)
	{
		this.items = items;
	}

	@JsonProperty("result")
	public List<Integer> getItems()
	{
		return items;
	}

	@JsonProperty("items")
	public void setItems(final List<Integer> items)
	{
		this.items = items;
	}
}
