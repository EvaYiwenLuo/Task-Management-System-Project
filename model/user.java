package com.model;

import org.hibernate.validator.constraints.Email;

public class user {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	private String id = null;
	private String name = null;
	private String college = null;
	private String major = null;
	private String classes = null;
	private String email = null;
	private String direction = null;

	public user(String id,String name,String college,String major,String classes,String email,String direction) {
      this.id = id;
      this.name = name;
      this.college = college;
      this.major = major;
      this.classes = classes;
      this.email = email;
      this.direction = direction;
	}

}
