package com.newche.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.newche.model.Tag;

@Document
public class User {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String email;
	@Indexed(unique = true)
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String role;  	  // role of the user admin etc.
    @DBRef
    private List<Tag> tagIds;
	
	private static final Logger logger = LoggerFactory.getLogger(News.class);
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String email, String userName, String password, String name, String surname, String role, List<Tag> tagIds) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.tagIds = tagIds;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		logger.debug("ID set to: {}", id);
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	public List<Tag> getTagIds() {
		return tagIds;
	}
	public void setTagIds(List<Tag> tags) {
		this.tagIds = tags;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", userName=" + userName + ", password=" + password + ", name="
				+ name + ", surname=" + surname + ", role=" + role + ", tagIds=" + tagIds + "]";
	}
	
}


