/**
 * 
 */
package com.zengfxdev.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.zengfxdev.dto.User;
import com.zengfxdev.dto.UserQueryCondition;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@PostMapping
	public User create(@RequestBody User user) {
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@GetMapping()
	@JsonView(User.UserSimpleView.class)
	public List<User> query(UserQueryCondition condition,
			@PageableDefault(page = 2, size = 17, sort = "username.asc") Pageable pageable) {
//		System.out.println(username);
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());

		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	@GetMapping(value = "/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable String id) {
		User user = new User();
		user.setUsername("zengfxdev");
		return user;
	}

}
