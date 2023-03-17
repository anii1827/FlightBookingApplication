package com.flightApplication.pdfGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long UserId;
	
	@Column
	private String Username;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "Group_Id")
	private UserGroup group;
	
	public UserGroup getGroup() {
		return group;
	}

	public User setGroup(UserGroup group) {
		this.group = group;
		return this;
	}

	public Long getUserId() {
		return UserId;
	}
	
	public User() {
	}

	public User setUserId(Long userId) {
		UserId = userId;
		return this;
	}

	public String getUsername() {
		return Username;
	}

	public User setUsername(String username) {
		Username = username;
		return this;
	}

	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", Username=" + Username + "]";
	}
}
