package fr.esgi.pa.editing_together_api.app.auth.usecase;

import fr.esgi.pa.editing_together_api.app.auth.infrastructure.dtos.request.LoginRequest;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.dtos.response.JwtResponse;
import fr.esgi.pa.editing_together_api.config.security.jwt.JwtUtils;
import fr.esgi.pa.editing_together_api.config.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SignIn {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public JwtResponse execute(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }
}
