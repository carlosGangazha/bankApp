package com.zimsec.Security.userAuth;

import com.zimsec.Security.Accounts.AccountModel;
import com.zimsec.Security.Accounts.AccountsRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AccountsRepository accountsRepository;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager,AccountsRepository accountsRepository){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.accountsRepository = accountsRepository;
    }

    public AuthenticationResponseDto register(UserCreateRequestDto request) {
        var user = new User(
                request.getFull_name(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                ERole.USER);

        var user_account = new AccountModel();
        user_account.setBalance(0.00);
        user_account.setUser(user);
        //adding the bank account to the bank before saving
        user.setAccount(user_account);


        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDto(jwtToken);
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDto(jwtToken);

    }
}
