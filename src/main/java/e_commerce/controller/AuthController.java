package e_commerce.controller;

import e_commerce.config.JwtProvider;
import e_commerce.domain.USER_ROLE;
import e_commerce.model.User;
import e_commerce.model.VerificationCode;
import e_commerce.repository.UserRepository;
import e_commerce.response.ApiResponse;
import e_commerce.response.AuthResponse;
import e_commerce.response.SignupRequest;
import e_commerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHabdler(@RequestBody SignupRequest request) throws Exception {
        String jwt=authService.createUser(request);
        AuthResponse response=new AuthResponse();
        response.setJwtToken(jwt);
        response.setMessage("Registration Successful");
        response.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHabdler(@RequestBody VerificationCode request) throws Exception {
        authService.sentLoginOtp(request.getEmail());
        ApiResponse response=new ApiResponse();

        response.setMessage("otp sent Successful");


        return ResponseEntity.ok(response);
    }
}
