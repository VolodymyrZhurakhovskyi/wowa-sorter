package com.wowa.sorter.service.impl;

import java.util.List;
import java.util.Map;

import com.wowa.sorter.service.SortingService;
import com.wowa.sorter.strategy.SortingStrategy;

public class DefaultSortingService implements SortingService
{
	private Map<String, SortingStrategy> strategies;

	@Override
	public void sort(final List<Integer> items, String method)
	{
		final SortingStrategy sortingStrategy = getStrategies().get(method);
		if (sortingStrategy == null)
		{
			throw new IllegalArgumentException(
					"Method " + method + " is not supported. Available methods are: " + getStrategies().keySet()+".");
		}
		if (items == null)
		{
			throw new IllegalArgumentException("'items' element is absent.");
		}
		sortingStrategy.sort(items);
	}

	public Map<String, SortingStrategy> getStrategies()
	{
		return strategies;
	}

	public void setStrategies(final Map<String, SortingStrategy> strategies)
	{
		this.strategies = strategies;
	}
}
