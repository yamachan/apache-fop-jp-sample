package dao;

import dto.ClientDTO;

public class ClientDAO {
	public static ClientDTO getClient(long id) {
		return new ClientDTO(id, "name of " + id, 10 + (int)id);
	}
}
