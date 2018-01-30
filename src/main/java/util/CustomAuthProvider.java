package util;

import db.dao.user.UserDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import util.CustomPasswordEncoder;

import java.util.ArrayList;

public class CustomAuthProvider implements AuthenticationProvider {

    private UserDataDAO userDataDAO;

    //private CustomPasswordEncoder passwordEncoder;

    public UserDataDAO getUserDataDAO() {
        return userDataDAO;
    }

    @Autowired
    public void setUserDataDAO(UserDataDAO userDataDAO) {
        this.userDataDAO = userDataDAO;
    }

//    public CustomPasswordEncoder getPasswordEncoder() {
//        return passwordEncoder;
//    }
//
//    @Autowired
//    public void setPasswordEncoder(CustomPasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        ArrayList list = new ArrayList();
        String pass = userDataDAO.getByLogin(name).get().getPassword();
        list.add(new SimpleGrantedAuthority("role_user"));
        //if (passwordEncoder.matches(password, pass)) {
        if (password.equals(pass)) {
            return new UsernamePasswordAuthenticationToken(name, pass, list);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
