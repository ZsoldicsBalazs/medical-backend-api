package ro.blz.medical.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Patient extends BaseEntity<Long>{

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String phone;



    @Builder
    public Patient(String username,String password, Role role, String email,String phone, String firstName, String lastName) {
        super.setUsername(username);
        super.setPassword(password);
        super.setRole(role);
        super.setEmail(email);
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+getRole()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}
