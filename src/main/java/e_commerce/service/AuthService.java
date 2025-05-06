package e_commerce.service;

import e_commerce.response.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email) throws Exception;
    String createUser(SignupRequest request) throws Exception;
}
