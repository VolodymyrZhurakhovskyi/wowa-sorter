package com.wowa.sorter;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.object.IsCompatibleType.typeCompatibleWith;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wowa.sorter.controller.SortingController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = "/application-context.xml",classes=SortingController.class)
public class WowaSortingControllerTests
{
	private static final String QUICK_SORTING_URL = "/sorting/quick";
	private static final String BUBBLE_SORTING_URL = "/sorting/bubble";
	private static final String BUILTIN_SORTING_URL = "/sorting/builtin";
	private static final String SORTING_WRONG_URL = "/sorting/wrong";
	private static final String LEGAL_ITEMS_JSON = "{\"items\": [9,6,3,5,4,2,5,10]}";
	private static final String ILLEGAL_ITEMS_JSON = "{\"items\": [9,6,3,5,4,2,5,\"A\"]}";

	@Resource
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
	}

	@Test
	public void sortingWrongUrlTest() throws Exception
	{
		final MvcResult result = this.mockMvc.perform(post(SORTING_WRONG_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(LEGAL_ITEMS_JSON))
				.andExpect(status().isNotAcceptable())//status 406
				.andReturn();
		assertThat(result.getResolvedException().getClass(),typeCompatibleWith(IllegalArgumentException.class));
	}

	@Test
	public void quickSortingSuccessfulTest() throws Exception
	{
		this.mockMvc.perform(post(QUICK_SORTING_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(LEGAL_ITEMS_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.result", hasSize(8)))
				.andExpect(jsonPath("$.result", contains(2, 3, 4, 5, 5, 6, 9, 10)));
	}

	@Test
	public void quickSortingIllegalItemTest() throws Exception
	{
		final MvcResult result = this.mockMvc.perform(post(QUICK_SORTING_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(ILLEGAL_ITEMS_JSON))
				.andExpect(status().isBadRequest())//status 400
				.andReturn();
		assertThat(result.getResolvedException().getClass(), typeCompatibleWith(HttpMessageNotReadableException.class));
	}

	@Test
	public void bubbleSortingSuccessfulTest() throws Exception
	{
		this.mockMvc.perform(post(BUBBLE_SORTING_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(LEGAL_ITEMS_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.result", hasSize(8)))
				.andExpect(jsonPath("$.result", contains(2, 3, 4, 5, 5, 6, 9, 10)));
	}

	@Test
	public void bubbleSortingIllegalItemTest() throws Exception
	{
		final MvcResult result = this.mockMvc.perform(post(BUBBLE_SORTING_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(ILLEGAL_ITEMS_JSON))
				.andExpect(status().isBadRequest())//status 400
				.andReturn();
		assertThat(result.getResolvedException().getClass(), typeCompatibleWith(HttpMessageNotReadableException.class));
	}

	@Test
	public void builtinSortingSuccessfulTest() throws Exception
	{
		this.mockMvc.perform(post(BUILTIN_SORTING_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(LEGAL_ITEMS_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.result", hasSize(8)))
				.andExpect(jsonPath("$.result", contains(2, 3, 4, 5, 5, 6, 9, 10)));
	}

	@Test
	public void builtinSortingIllegalItemTest() throws Exception
	{
		final MvcResult result = this.mockMvc.perform(post(BUILTIN_SORTING_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(ILLEGAL_ITEMS_JSON))
				.andExpect(status().isBadRequest())//status 400
				.andReturn();
		assertThat(result.getResolvedException().getClass(), typeCompatibleWith(HttpMessageNotReadableException.class));
	}

}
