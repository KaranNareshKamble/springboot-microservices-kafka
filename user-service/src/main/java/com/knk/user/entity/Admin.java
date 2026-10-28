package com.knk.user.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;
	
	@NotBlank(message = "mail is required")
	@Email(message = "email must be valid")
	private String mail;
	
	@NotNull(message = "age is required")
	@Min(value = 18, message = "age must be atleast 18")
	@Max(value = 100, message = "age can not exceed 100")
	private Long age;
	
	@NotBlank(message = "phone no must be valid")
	@Pattern(regexp = "^[0-9]{10}$", message = "phone must be valid")
	private String phone;
	
	@NotNull(message = "DOB is required")
	@Past(message = "DOB must be valid")
	@JsonProperty("dob")
	private LocalDate DOB;
	
	@NotNull(message = "active is required")
	private Boolean isActive;
	
}
