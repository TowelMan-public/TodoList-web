package com.example.demo.form.delete;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.RegexpMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteNoneUserInSpaceForm {
	@NotNull(message=RegexpMessage.INTEGER)
	private Integer id;
}
