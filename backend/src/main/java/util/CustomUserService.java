package util;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserService implements UserDetailsService {

//    private UserDataDAO userDataDAO;
//
//    @Autowired
//    public void setUserDataDAO(UserDataDAO userDataDAO) {
//        this.userDataDAO = userDataDAO;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Result<UserData> result = userDataDAO.getByLogin(username);
//        if (result.isSuccess()) {
//
//            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//            grantedAuthorityList.add(new SimpleGrantedAuthority(result.get().getState().getRole()));
//
//            return new InnerUser(
//                    result.get().getId(),
//                    result.get().getLogin(),
//                    result.get().getPassword(),
//                    result.get().getPerson().getFirstName(),
//                    result.get().getPerson().getLastName(),
//                    result.get().getPerson().getBirthday(),
//                    true,
//                    false,
//                    false,
//                    grantedAuthorityList);
//        }
//        throw new UsernameNotFoundException("No such user");
        return null;
    }
}
