package com.example.demo.form.insert;

import javax.validation.constraints.NotBlank;

import com.example.demo.RegexpMessage;
import com.example.demo.form.delete.DeleteNoneUserInSpaceForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsertContentForm {
	@NotBlank(message=RegexpMessage.EMPTY)
	private String contentTitle;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String contentText;
}
