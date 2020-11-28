package dto;

import lombok.Data;

@Data
public class ClientDTO {
	public ClientDTO(long id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	private long id;
	private String name;
	private int age;
}