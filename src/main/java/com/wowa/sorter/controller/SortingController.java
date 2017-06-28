package com.wowa.sorter.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wowa.sorter.dto.ErrorDetailsDTO;
import com.wowa.sorter.dto.ItemsListDTO;
import com.wowa.sorter.service.SortingService;

@RestController
@EnableWebMvc
public class SortingController
{
	@Resource(name = "defaultSortingService")
	private SortingService sortingService;

	@RequestMapping(value = "/sorting/{method}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity sorting(@RequestBody ItemsListDTO list, @PathVariable String method)
	{
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		final List items = list.getItems();
		sortingService.sort(items, method);

		return new ResponseEntity<>(list, httpHeaders, HttpStatus.OK);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ErrorDetailsDTO handleException(HttpServletRequest req, IllegalArgumentException ex)
	{
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage(), req.getRequestURI());
		return errorDetails;
	}


}
