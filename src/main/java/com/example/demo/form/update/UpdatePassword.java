package com.example.demo.form.update;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import com.example.demo.RegexpMessage;
import com.example.demo.form.delete.DeleteNoneUserInSpaceForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatePassword {
	@NotBlank(message=RegexpMessage.EMPTY)
	private String newPassword;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String oneMorePassword;
	
	@AssertTrue(message = "2つのパスワードが合致しません。もう一度お確かめください")
	public boolean isNotMatchesPassword() {
		return (newPassword == null || oneMorePassword == null) || (newPassword.equals(oneMorePassword));
	}
}
