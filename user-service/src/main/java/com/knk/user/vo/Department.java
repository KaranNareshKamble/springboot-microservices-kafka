package com.knk.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

	private Long departmentId;
	private String departmentName;
	private Long departmentCode;
}
