package com.example.demo.form.insert;

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
public class InsertSpaceForm {
	@NotBlank(message=RegexpMessage.EMPTY)
	private String spaceName;
	@Pattern(regexp = RegexpPattern.SPACE, message = RegexpMessage.SPACE)
	private String scopeId;
}
