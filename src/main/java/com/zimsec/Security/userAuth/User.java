package com.zimsec.Security.userAuth;

import com.zimsec.Security.Accounts.AccountModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AccountModel account;

    public User(String firstname, String email, String encode, ERole role) {
        this.firstName = firstname;
        this.email = email;
        this.password = encode;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        return email;
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
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getFirstName() {
        return firstName;
    }
    public AccountModel getAccount() {
        return account;
    }
    public void setAccount(AccountModel account) {
        this.account = account;
    }
    public int getId() {
        return id;
    }
}
