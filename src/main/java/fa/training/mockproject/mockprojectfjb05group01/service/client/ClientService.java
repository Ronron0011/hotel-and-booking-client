package fa.training.mockproject.mockprojectfjb05group01.service.client;

import fa.training.mockproject.mockprojectfjb05group01.dto.client.ClientDTO;
import fa.training.mockproject.mockprojectfjb05group01.dto.request.RegisterRequestDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.client.Client;

public interface ClientService {
    ClientDTO convertEntityToDto(Client client);

    Client convertDtoToEntity(ClientDTO clientData);

    Client convertRegisterDtoToEntity(RegisterRequestDTO registerRequestDTO);

    void updateClient(ClientDTO clientData);

    ClientDTO findClientByEmail(String email);

    boolean existsClientByEmail(String email);

    boolean existsClientByPhone(String phone);

    void createClient(ClientDTO clientData);

    void registerClient(RegisterRequestDTO registerRequestDTO);
}
