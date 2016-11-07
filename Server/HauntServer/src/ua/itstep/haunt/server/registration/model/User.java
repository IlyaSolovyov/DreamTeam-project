package ua.itstep.haunt.server.registration.model;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long id;
	@Column(unique = true, nullable = false, length = 20)
	private String username;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(name = "password")
	private String passHash;

	@Column(name = "salt")
	private String saltString;

	User() throws NoSuchAlgorithmException, NoSuchProviderException {
		username = "";
		email = "";
		passHash = "";
		saltString = "";

	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;

	}

	public String getEmail() {
		return email;
	}

	public void setSaltString(String salt) {
		saltString = salt;
	}

	public String getSaltString() {
		return saltString;
	}

	public void setPass(String password) {
		passHash = password;
	}

	public String getPass() {
		return passHash;
	}

}
