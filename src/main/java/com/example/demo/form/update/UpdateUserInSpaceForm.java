package com.example.demo.form.update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.demo.RegexpMessage;
import com.example.demo.RegexpPattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserInSpaceForm {
	@Pattern(regexp = RegexpPattern.INTEGER, message = RegexpMessage.INTEGER)
	private String spaceId;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String username;
	@Pattern(regexp = RegexpPattern.AUTHORITY, message = RegexpMessage.AUTHORITY)
	private String authortyId;
}
