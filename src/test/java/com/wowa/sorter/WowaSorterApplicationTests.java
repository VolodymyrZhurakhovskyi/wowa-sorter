package com.wowa.sorter;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.wowa.sorter.controller.SortingController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = "/application-context.xml",classes=SortingController.class)
public class WowaSorterApplicationTests
{
	@Resource
	private SortingController sortingController;

	@Test
	public void contextLoads()
	{
		assertThat(sortingController).isNotNull();
	}
}
