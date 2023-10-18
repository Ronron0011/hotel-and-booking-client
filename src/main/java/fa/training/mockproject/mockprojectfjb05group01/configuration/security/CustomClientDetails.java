package fa.training.mockproject.mockprojectfjb05group01.configuration.security;

import fa.training.mockproject.mockprojectfjb05group01.entity.client.Client;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomClientDetails implements UserDetails {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private boolean activationStatus;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    //map tu thong tin client chuyen sang thong tin customerDetails
    public static CustomClientDetails mapUserToUserDetails(Client client) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(client.getRole().getRoleType().name());
        return new CustomClientDetails(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPassword(),
                client.getPhone(),
                client.getActivationStatus(),
                Collections.singleton(authority)
        );
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.activationStatus;
    }
}
