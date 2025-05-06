package e_commerce.service.impl;

import e_commerce.config.JwtProvider;
import e_commerce.domain.USER_ROLE;
import e_commerce.model.Cart;
import e_commerce.model.User;
import e_commerce.model.VerificationCode;
import e_commerce.repository.CartRepository;
import e_commerce.repository.UserRepository;
import e_commerce.repository.VerificationCodeRepository;
import e_commerce.response.SignupRequest;
import e_commerce.service.AuthService;
import e_commerce.service.EmailService;
import e_commerce.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;

    @Override
    public void sentLoginOtp(String email) throws Exception {
        String SIGNING_PREFIX="signin_";

        if(email.startsWith(SIGNING_PREFIX)){
            email=email.substring(SIGNING_PREFIX.length());
            User user=userRepository.findByEmail(email);

            if(user==null) {
                throw new Exception("User not exist with provided email");
            }
        }
        VerificationCode isExist=verificationCodeRepository.findByEmail(email);
        if(isExist!=null) {
            verificationCodeRepository.delete(isExist);
        }

        String otp = OtpUtil.generateOtp();

        VerificationCode verificationCode=new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);


        String subject = "js campos login/signup otp";
        String text = "your login/signup otp has been generated =" +otp;

        emailService.sendVerificationEmail(email,otp,subject,text);


    }

    @Override
    public String createUser(SignupRequest request) throws Exception {


        VerificationCode verificationCode = verificationCodeRepository.findByEmail(request.getEmail());

        if(verificationCode == null || !verificationCode.getOtp().equals(request.getOtp())) {
            throw new Exception();
        }

        User user = userRepository.findByEmail(request.getEmail());
        if(user == null) {
            User createdUser = new User();
            createdUser.setEmail(request.getEmail());
            createdUser.setFullName(request.getFullname());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("6182463777");
            createdUser.setPassword(passwordEncoder.encode(request.getOtp()));
            user = userRepository.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);


        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return jwtProvider.generateToken(authentication);
    }
}
