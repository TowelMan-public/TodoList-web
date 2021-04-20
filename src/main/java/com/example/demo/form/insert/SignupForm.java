package com.example.demo.form.insert;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import com.example.demo.RegexpMessage;

import lombok.Data;

@Data
public class SignupForm {
	@NotBlank(message=RegexpMessage.EMPTY)
	private String username;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String password;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String oneMorePassword;
	
	@AssertTrue(message = "2つのパスワードが合致しません。もう一度お確かめください")
	public boolean notMatchesPassword() {
		return (password == null || oneMorePassword == null) || (password.equals(oneMorePassword));
	}
}
