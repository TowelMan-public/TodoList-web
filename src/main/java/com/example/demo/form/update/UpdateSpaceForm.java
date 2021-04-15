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
public class UpdateSpaceForm {
	@NotBlank(message=RegexpMessage.EMPTY)
	private String spaceName;
	@Pattern(regexp = RegexpPattern.INTEGER, message = RegexpMessage.INTEGER)
	private String spaceId;
	@Pattern(regexp = RegexpPattern.SPACE, message = RegexpMessage.SPACE)
	private String scopeId;
}
