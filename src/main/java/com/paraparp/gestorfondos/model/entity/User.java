package com.paraparp.gestorfondos.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

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

	@Column(name = "first_name")
	@NotNull
	@NotEmpty(message = "first name must not be empty")
	private String firstName;

	@Column(name = "last_name")
	@NotNull
	@NotEmpty(message = "last name must not be empty")
	private String lastName;

	@Column(name = "username", unique = true)
	@NotNull
	private String username;

	@Column(name = "email")
	@NotNull
	@NotEmpty(message = "email must not be empty")
	@Email(message = "email should be a valid email")
	private String email;

	@Column(name = "creation_date")
	@CreationTimestamp
	private LocalDate creationDate;

	@Column(name = "password", length = 100)
	@NotNull
	private String password;
	
	@Column(name = "avatar")
	private String avatar;

////	@Column(name = "google", columnDefinition = "boolean default false")
//	private boolean google = true;
//
////	@Column(name = "enabled", columnDefinition = "boolean default true")
	private boolean enabled;



	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Portfolio> portfolios;

}
