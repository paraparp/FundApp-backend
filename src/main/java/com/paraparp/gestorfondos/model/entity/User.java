package com.paraparp.gestorfondos.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name", nullable = false)
	@NotEmpty(message = "first name must not be empty")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@NotEmpty(message = "last name must not be empty")
	private String lastName;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "email", nullable = false)
	@NotEmpty(message = "email must not be empty")
	@Email(message = "email should be a valid email")
	private String email;

	@Column(name = "creation_date")
	private LocalDate creationDate;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "google", columnDefinition = "boolean default false")
	private boolean google;

	@Column(name = "enabled", columnDefinition = "boolean default true")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
	@UniqueConstraint(columnNames = { "user_id", "role_id" }) })
	private List<Role> roles;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Portfolio> portfolios;

}
