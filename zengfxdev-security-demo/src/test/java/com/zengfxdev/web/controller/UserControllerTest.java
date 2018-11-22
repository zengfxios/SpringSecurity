/**
 * 
 */
package com.zengfxdev.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockmvc;

	@Before
	public void setup() {
		mockmvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockmvc.perform(
				get("/user").param("username", "zengfxdev").param("age", "16").param("ageto", "25").param("size", "15")
//						.param("page", "3").param("sort", "age.desc")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3)).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
	}

	@Test
	public void whenGenInfoSuccess() throws Exception {
		String result = mockmvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.username").value("zengfxdev")).andReturn()
				.getResponse().getContentAsString();

		System.out.println(result);
	}

	@Test
	public void whenGetInfoFail() throws Exception {
		mockmvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void whenCreateSuccess() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"username\":\"zengfxdev\",\"password\":\"null\",\"birthday\":" + date.getTime() + "}";
		String result = mockmvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1")).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
	}

}
