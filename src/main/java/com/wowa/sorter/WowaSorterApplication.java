package com.wowa.sorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("application-context.xml")
public class WowaSorterApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(WowaSorterApplication.class, args);
	}
}
