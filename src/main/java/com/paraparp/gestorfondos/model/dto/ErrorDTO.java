package com.paraparp.gestorfondos.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ErrorDTO {

	private int httpErrorCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String description = "";
	private String path;
	private List<String> errors;

}
