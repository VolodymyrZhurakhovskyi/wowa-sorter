package com.wowa.sorter.strategy.impl;

import java.util.Collections;
import java.util.List;

import com.wowa.sorter.strategy.SortingStrategy;

public class BuiltinSortingStrategy implements SortingStrategy
{
	@Override
	public void sort(final List<Integer> items)
	{
		Collections.sort(items);
	}
}
