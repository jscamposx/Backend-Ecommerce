package e_commerce.service;

import e_commerce.response.SignupRequest;

public interface AuthService {

    String createUser(SignupRequest request);
}
