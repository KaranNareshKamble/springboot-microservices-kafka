package com.knk.user.vo;

import com.knk.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTempVO {

	private Department department;
	private User user;
}
