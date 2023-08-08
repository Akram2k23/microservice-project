package com.tap.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

//import lombok.*;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class User {
	
	@Id
	private String userId;
	private String name;
	private String email;
	private String about;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String userId, String name, String email, String about, List<Rating> ratings) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.about = about;
		this.ratings = ratings;
	}
	
	public User(String userId, String name, String email, String about) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.about = about;
	}
	
	


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public List<Rating> getRatings() {
		return ratings;
	}


	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	
//	Create Builder pattern
	
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	public static class UserBuilder{
		
		private String userId;
		private String name;
		private String email;
		private String about;
		
		public UserBuilder setUserId(final String userId) {
			this.userId = userId;
			return this;
		}

		public UserBuilder setName(final String name) {
			this.name = name;
			return this;
		}

		public UserBuilder setEmail(final String email) {
			this.email = email;
			return this;
		}

		public UserBuilder setAbout(final String about) {
			this.about = about;
			return this;
		}

		public User build() {
			return new User(userId, name, email, about);
		}
		
		
	}
	
	
	


	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", about=" + about + ", ratings="
				+ ratings + "]";
	}
	
	

}
