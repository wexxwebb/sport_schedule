package util;

import db.entities._inter.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import services._inter.UserService;

import java.util.ArrayList;

public class CustomAuthProvider implements AuthenticationProvider {

    private UserService userService;

    private CustomPasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(CustomPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserData userData = userService.getUserByLogin(name);
        if (userData != null) {
            if (passwordEncoder.matches(password, userData.getPassword())) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userData.getLogin());
                ArrayList<GrantedAuthority> list = new ArrayList<>(userDetails.getAuthorities());
                list.add(new SimpleGrantedAuthority("ROLE_USER"));
                return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), list);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}