package com.flightApplication.pdfGenerator;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class UserGroup {
	
		@Id 
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long GroupId;
		
		private String GroupName;
		
		public UserGroup() {
			
		}
		
//		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//		@JoinColumn(name = "group_id")
//		Set<User> user = new HashSet<>();
//		
//		public Set<User> getUser() {
//			return user;
//		}
//
//		public UserGroup setUser(Set<User> user) {
//			this.user = user;
//			return this;
//		}

		public Long getGroupId() {
			return this.GroupId;
		}

		public UserGroup setGroupId(Long groupId) {
			this.GroupId = groupId;
			return this;
		}

		public String getGroupName() {
			return this.GroupName;
		}

		public UserGroup setGroupName(String groupName) {
			this.GroupName = groupName;
			return this;
		}

		@Override
		public String toString() {
			return "UserGroup [GroupId=" + GroupId + ", GroupName=" + GroupName + "]";
		}
		
}
