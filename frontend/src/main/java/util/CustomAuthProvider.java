package util;

import common.Logged;
import db.entities._inter.UserData;
import org.apache.log4j.Logger;
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
import services.excep.ServiceIsNotAvailableException;

import java.util.ArrayList;

public class CustomAuthProvider implements AuthenticationProvider {

    @Logged
    private Logger logger;

    private UserService userService;

    private CustomPasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    @Autowired
    public CustomAuthProvider(UserService userService, CustomPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserData userData;
        try {
            userData = userService.getUserByLogin(name);
        } catch (ServiceIsNotAvailableException e) {
            return null;
        }
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