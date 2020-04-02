package com.paraparp.gestorfondos.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {
	
	private static final long serialVersionUID = 1L;

	
	private long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;	
	private Date creationDate;
	private String password;
//	@Transient
//	private String passwordConfirm;
	private String role;
	private boolean google;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "username", nullable = false, unique=true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="creation_date")
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Column(name = "password", length = 100, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="role")
//	@Enumerated(EnumType.STRING)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	@Column(name="google", columnDefinition = "boolean default false")
	public boolean isGoogle() {
		return google;
	}

	public void setGoogle(boolean google) {
		this.google = google;
	}




//	public enum Role {
//		ADMIN,USER
//	}

}



