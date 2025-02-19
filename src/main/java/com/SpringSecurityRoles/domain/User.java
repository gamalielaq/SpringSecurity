package com.SpringSecurityRoles.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Serializable, la razón por la que necesitamos hacer esto es porque esta clase dejará de ser una clase de Java
@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String profileImageUrl;
	private Date lastLoginDate;
	private Date getLastLoginDisplay;
	private String roles; //ROLE_USER{read, edit}, ROLE_ADMIN{delete}
	private String[] authorities;
	private boolean isActive;
	private boolean isNotLocked;

	private Date lastLoginDateDisplay;
	private Date joinDate;
	
	public User() {};
	
	public User(Long id, String userId, String firstName, String lastName, String userName, String password, String email, String profileImageUrl, Date lastLoginDate, Date getLastLoginDisplay, String roles, String[] authorities, boolean isActive, boolean isNotLocked, Date lastLoginDateDisplay, Date joinDate) {
		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
		this.lastLoginDate = lastLoginDate;
		this.getLastLoginDisplay = getLastLoginDisplay;
		this.roles = roles;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;

		this.lastLoginDateDisplay = lastLoginDateDisplay;
		this.joinDate = joinDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getGetLastLoginDisplay() {
		return getLastLoginDisplay;
	}
	public void setGetLastLoginDisplay(Date getLastLoginDisplay) {
		this.getLastLoginDisplay = getLastLoginDisplay;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String[] getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isNotLocked() {
		return isNotLocked;
	}
	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}
	public Date getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }
	public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }
	public Date getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}

