package util;


import db.entities._inter.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import services._inter.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserData userData = userService.getUserByLogin(username);
        if (userData != null) {

            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add(new SimpleGrantedAuthority(userData.getRole()));

            return new InnerUser(
                    userData.getId(),
                    userData.getLogin(),
                    userData.getPassword(),
                    userData.getPerson().getFirstName(),
                    userData.getPerson().getLastName(),
                    userData.getPerson().getBirthday(),
                    true,
                    false,
                    false,
                    grantedAuthorityList);
        }
        throw new UsernameNotFoundException("No such user");
    }
}
