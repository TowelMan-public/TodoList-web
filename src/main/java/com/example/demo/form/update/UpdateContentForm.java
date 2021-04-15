package com.example.demo.form.update;

import javax.validation.constraints.NotBlank;

import com.example.demo.RegexpMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateContentForm {
	@NotBlank(message=RegexpMessage.INTEGER)
	private String contentId;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String contentTitle;
	@NotBlank(message=RegexpMessage.EMPTY)
	private String contentText;
}
