package dto;

import java.util.Date;
import lombok.Data;

@Data
public class DocumentDTO {
	public DocumentDTO(Date start, String name, String format) {
		super();
		this.start = start;
		this.name = name;
		this.format = format;
	}
	private Date start;
	private String name;
	private String format;
}
