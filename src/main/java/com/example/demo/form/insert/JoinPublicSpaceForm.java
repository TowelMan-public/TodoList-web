package com.example.demo.form.insert;

import javax.validation.constraints.NotBlank;

import com.example.demo.RegexpMessage;
import lombok.Data;

@Data
public class JoinPublicSpaceForm {
	@NotBlank(message=RegexpMessage.INTEGER)
	private Integer spaceId;
}
