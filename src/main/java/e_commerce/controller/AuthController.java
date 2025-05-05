package e_commerce.controller;

import e_commerce.model.User;
import e_commerce.repository.UserRepository;
import e_commerce.response.SignupRequest;
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

    @PostMapping("/signup")
    public ResponseEntity<User> createUserHabdler(@RequestBody SignupRequest req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullname());

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
