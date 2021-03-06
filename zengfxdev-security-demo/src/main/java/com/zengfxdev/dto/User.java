/**
 * 
 */
package com.zengfxdev.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Administrator
 *
 */
public class User {

	public interface UserSimpleView {
	};

	public interface UserDetailView extends UserSimpleView {
	};

	private String id;
	private String username;
	private String password;
	private Date birthday;

	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
