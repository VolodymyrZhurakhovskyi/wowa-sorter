package com.wowa.sorter.strategy.impl;

import java.util.Collections;
import java.util.List;

import com.wowa.sorter.strategy.SortingStrategy;

public class BubbleSortingStrategy implements SortingStrategy
{
	@Override
	public void sort(final List<Integer> items)
	{
		boolean swapped;
		do
		{
			swapped = false;
			for (int i = 1; i <= items.size() - 1; i++)
			{
				if (items.get(i - 1) > items.get(i))
				{
					Collections.swap(items, i - 1, i);
					swapped = true;
				}
			}
		} while (swapped);
	}
}
