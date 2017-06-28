package com.wowa.sorter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Ordering;
import com.wowa.sorter.service.SortingService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = "/application-context.xml")
public class SortingServiceTests
{
	private static final String BUBBLE_SORTING_METHOD = "bubble";
	private static final String QUICK_SORTING_METHOD = "quick";
	private static final String BUILTIN_SORTING_METHOD = "builtin";

	@Resource(name = "defaultSortingService")
	private SortingService sortingService;

	private List items;

	@Before
	public void resetItemsList() {
		items = new ArrayList<Integer>()
		{{
				add(9);
				add(6);
				add(3);
				add(5);
				add(4);
				add(2);
				add(5);
				add(10);
			}};
	}

	@Test
	public void sortingServiceExistingTest()
	{
		assertThat(sortingService).isNotNull();
	}

	@Test
	public void bubbleSortingTest()
	{
		assertFalse(Ordering.natural().isOrdered(items));
		sortingService.sort(items, BUBBLE_SORTING_METHOD);
		assertTrue(Ordering.natural().isOrdered(items));
	}

	@Test
	public void quickSortingTest()
	{
		assertFalse(Ordering.natural().isOrdered(items));
		sortingService.sort(items, QUICK_SORTING_METHOD);
		assertTrue(Ordering.natural().isOrdered(items));
	}

	@Test
	public void builtinSortingTest()
	{
		assertFalse(Ordering.natural().isOrdered(items));
		sortingService.sort(items, BUILTIN_SORTING_METHOD);
		assertTrue(Ordering.natural().isOrdered(items));
	}
}
