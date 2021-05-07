package fr.esgi.pa.editing_together_api.app.auth.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.auth.domain.exceptions.AlreadyCreatedException;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.dtos.request.LoginRequest;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.dtos.request.SignupRequest;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.dtos.response.JwtResponse;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.dtos.response.MessageResponse;
import fr.esgi.pa.editing_together_api.app.auth.usecase.SignIn;
import fr.esgi.pa.editing_together_api.app.auth.usecase.SignUp;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final SignIn signIn;

    private final SignUp signUp;


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(signIn.execute(loginRequest));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws NotFoundException, AlreadyCreatedException {
        signUp.execute(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
