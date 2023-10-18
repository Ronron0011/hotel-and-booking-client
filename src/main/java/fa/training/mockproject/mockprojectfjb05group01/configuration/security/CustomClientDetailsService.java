package fa.training.mockproject.mockprojectfjb05group01.configuration.security;

import fa.training.mockproject.mockprojectfjb05group01.entity.client.Client;
import fa.training.mockproject.mockprojectfjb05group01.repository.client.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomClientDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public CustomClientDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Client> optionalClient = clientRepository.findClientByEmail(email);

        if (optionalClient.isEmpty()) {
            throw new UsernameNotFoundException("Client not found due to error(s) or not existing in the database.");
        }

        Client client = optionalClient.get();
        return CustomClientDetails.mapUserToUserDetails(client);
    }
}
