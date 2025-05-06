package e_commerce.service.impl;

import e_commerce.domain.USER_ROLE;
import e_commerce.model.Seller;
import e_commerce.model.User;
import e_commerce.repository.SellerRepository;
import e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerUserServiceimpl implements UserDetailsService {

   private UserRepository userRepository;
   private static final String SELLER_PREFIX = "seller";
   private final SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith(SELLER_PREFIX)) {
            String actualUsername = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUsername);

            if(seller!=null) {
                return buildUserDetails(seller.getEmail(), seller.getPassword(), seller.getRole());
            }
        }else{
            User user = userRepository.findByEmail(username);
            if (user == null) {
                return buildUserDetails(user.getEmail(),user.getPassword(),user.getRole());
            }
        }
        throw new UsernameNotFoundException("user or seller not found with email: " + username);
    }


    private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
        if(role==null) role=USER_ROLE.ROLE_CUSTOMER;
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE" +role));

        return new org.springframework.security.core.userdetails.User(
                email,
                password,
                authorityList);
    }
}
