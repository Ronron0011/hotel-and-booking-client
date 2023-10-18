package fa.training.mockproject.mockprojectfjb05group01.service.client.impl;

import fa.training.mockproject.mockprojectfjb05group01.dto.client.ClientDTO;
import fa.training.mockproject.mockprojectfjb05group01.dto.request.RegisterRequestDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.client.Client;
import fa.training.mockproject.mockprojectfjb05group01.entity.client.Role;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.RoleType;
import fa.training.mockproject.mockprojectfjb05group01.repository.client.ClientRepository;
import fa.training.mockproject.mockprojectfjb05group01.repository.client.RoleRepository;
import fa.training.mockproject.mockprojectfjb05group01.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository,
                             RoleRepository roleRepository) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ClientDTO convertEntityToDto(Client client) {
        ClientDTO clientData = new ClientDTO();
        clientData.setId(client.getId());
        clientData.setFirstName(client.getFirstName());
        clientData.setLastName(client.getLastName());
        clientData.setEmail(client.getEmail());
        clientData.setPhone(client.getPhone());
        clientData.setDateOfBirth(client.getDateOfBirth());
        clientData.setPassword(client.getPassword());
        clientData.setEmailVerificationStatus(client.getEmailVerificationStatus());
        clientData.setClientCode(client.getClientCode());
        clientData.setRoleId(client.getRole().getId());
        return clientData;
    }

    @Override
    public Client convertDtoToEntity(ClientDTO clientData) {
        Client client = new Client();
        client.setId(clientData.getId());
        client.setFirstName(clientData.getFirstName());
        client.setLastName(clientData.getLastName());
        client.setPhone(clientData.getPhone());
        client.setEmail(clientData.getEmail());
        client.setDateOfBirth(clientData.getDateOfBirth());
        client.setPassword(clientData.getPassword());
        client.setActivationStatus(clientData.getActivationStatus());
        client.setEmailVerificationStatus(clientData.getActivationStatus());
        client.setClientCode(clientData.getClientCode());
        client.setRole(roleRepository.getReferenceById(client.getId()));
        return client ;
    }

    @Override
    public Client convertRegisterDtoToEntity(RegisterRequestDTO registerRequestDTO) {
        Client client = new Client();
        client.setFirstName(registerRequestDTO.getFirstName());
        client.setLastName(registerRequestDTO.getLastName());
        client.setPhone(registerRequestDTO.getPhone());
        client.setEmail(registerRequestDTO.getEmail());
        client.setPassword(registerRequestDTO.getPassword());
        return client;
    }


    @Override
    public void updateClient(ClientDTO clientData) {
        Optional<Client> clientFind = clientRepository.findClientByEmail(clientData.getEmail());
        if (clientFind.isPresent()){
            Client client = clientFind.get();
            client.setFirstName(clientData.getFirstName());
            client.setLastName(clientData.getLastName());
            client.setPhone(clientData.getPhone());
            client.setDateOfBirth(clientData.getDateOfBirth());

            clientRepository.save(client);
        }
    }

    @Override
    public ClientDTO findClientByEmail(String email) {
        Client client = null;
        Optional<Client> clientFind = clientRepository.findClientByEmail(email);
        if (clientFind.isPresent()){
            client = clientFind.get();
        }
        assert client != null;
        return  convertEntityToDto(client);
    }

    @Override
    public boolean existsClientByEmail(String email) {
        return clientRepository.existsClientByEmail(email);
    }

    @Override
    public boolean existsClientByPhone(String phone) {
        return clientRepository.existsClientByPhone(phone);
    }

    @Override
    public void createClient(ClientDTO clientData) {
        clientRepository.save(convertDtoToEntity(clientData));
    }

    @Override
    public void registerClient(RegisterRequestDTO registerRequestDTO) {
        Client client = convertRegisterDtoToEntity(registerRequestDTO);
        client.setActivationStatus(true);
        client.setEmailVerificationStatus(false);
        Optional<Role> optionalRole = roleRepository.findRoleByRoleType(RoleType.ROLE_CLIENT);
        optionalRole.ifPresent(client::setRole);
        clientRepository.save(client);
    }

}
