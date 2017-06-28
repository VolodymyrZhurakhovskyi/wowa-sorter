package com.wowa.sorter.strategy.impl;

import java.util.Collections;
import java.util.List;

import com.wowa.sorter.strategy.SortingStrategy;

public class QuickSortingStrategy implements SortingStrategy
{
	@Override
	public void sort(final List<Integer> items)
	{
		quicksort(items, 0, items.size() - 1);
	}

	protected void quicksort(final List<Integer> items, final int lo, final int hi)
	{
		if (lo < hi)
		{
			final int p = partition(items, lo, hi);
			quicksort(items, lo, p - 1);
			quicksort(items, p + 1, hi);
		}
	}

	protected int partition(final List<Integer> items, final int lo, final int hi)
	{
		final Integer pivot = items.get(hi);
		int i = lo - 1;
		for (int j = lo; j <= hi; j++)
		{
			if (items.get(j) <= pivot)
			{
				i++;
				if (i != j)
				{
					Collections.swap(items, i, j);
				}
			}
		}
		return i;
	}
}
