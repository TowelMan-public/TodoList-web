package com.example.demo.form.insert;

import javax.validation.constraints.NotNull;

import com.example.demo.RegexpMessage;
import lombok.Data;

@Data
public class JoinPublicSpaceForm {
	@NotNull(message=RegexpMessage.INTEGER)
	private Integer spaceId;
}
