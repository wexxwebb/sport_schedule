package util;

import common.Result;
import db.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import services.UserService;

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

        Result<UserData> result = userService.getUserByLogin(name);
        if (result.isSuccess()) {
            if (passwordEncoder.matches(password, result.get().getPassword())) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(result.get().getLogin());
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