package util;


import common.Result;
import db.pojo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import services.UserService;

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

        Result<UserData> result = userService.getUserByLogin(username);
        if (result.isSuccess()) {

            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add(new SimpleGrantedAuthority(result.get().getState().getRole()));

            return new InnerUser(
                    result.get().getId(),
                    result.get().getLogin(),
                    result.get().getPassword(),
                    result.get().getPerson().getFirstName(),
                    result.get().getPerson().getLastName(),
                    result.get().getPerson().getBirthday(),
                    true,
                    false,
                    false,
                    grantedAuthorityList);
        }
        throw new UsernameNotFoundException("No such user");
    }
}
