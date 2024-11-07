package com.tech.pro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@NotBlank(message = "name is required")
	@Column(length = 40, nullable = false)
	protected String name;
	
	@NotBlank(message = "email is required")
	@Column(length = 100, nullable = false)
	protected String email;
	
	@NotBlank(message = "password is required")
	@Column(length = 200, nullable = false)
	protected String password;
		
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user-role",
				joinColumns = @JoinColumn(name = "client_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id")
			)	
	private List<Role> roles = new ArrayList<>();
	
	@Transient	
	private String confirmPassword;
	
	public boolean doesPasswordMatch() {
		return this.password.equals(confirmPassword);
	}
	
}
