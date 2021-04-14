package com.example.demo.form.delete;

import javax.validation.constraints.NotBlank;

import com.example.demo.RegexpMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteNoneUserInSpaceForm {
	@NotBlank(message=RegexpMessage.INTEGER)
	private String id;
}
