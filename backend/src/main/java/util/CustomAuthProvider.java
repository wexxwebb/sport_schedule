package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomAuthProvider implements AuthenticationProvider {

    //private UserDataDAO userDataDAO;

    private CustomPasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    public UserDataDAO getUserDataDAO() {
//        return userDataDAO;
//    }
//
//    @Autowired
//    public void setUserDataDAO(UserDataDAO userDataDAO) {
//        this.userDataDAO = userDataDAO;
//    }

    public CustomPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoder(CustomPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String name = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        ArrayList<GrantedAuthority> list = new ArrayList<>();
//
//        Result<UserData> result = userDataDAO.getByLogin(name);
//        if (result.isSuccess()) {
//            if (passwordEncoder.matches(password, result.get().getPassword())) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(result.get().getLogin());
//                list.addAll(userDetails.getAuthorities());
//                list.add(new SimpleGrantedAuthority("ROLE_USER"));
//                return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), list);
//            }
//        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}