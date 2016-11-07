package ua.itstep.haunt.server.registration.model;

import java.security.SecureRandom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tokens")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id", unique = true, nullable = false)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	private String value;

	public Token(User user) {
		this.user = user;
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		value = bytes.toString();
	}

	public String getValue() {
		return value;

	}
}
