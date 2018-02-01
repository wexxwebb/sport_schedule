package util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@SuppressWarnings("unused")
public class InnerUser implements UserDetails {

    private int id;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String birthday;
    private boolean enabled;
    private boolean locked;
    private boolean expired;
    private Collection<? extends GrantedAuthority> authorities;

    private void getUserFromContext(InnerUser innerUser) {
        this.id = innerUser.getId();
        this.username = innerUser.getUsername();
        this.password = innerUser.getPassword();
        this.enabled = innerUser.isEnabled();
        this.locked = innerUser.isAccountNonLocked();
        this.expired = innerUser.isCredentialsNonExpired();
        this.firstName = innerUser.getFirstName();
        this.lastName = innerUser.getLastName();
        this.birthday = innerUser.getBirthday();
        this.authorities = innerUser.getAuthorities();
    }

    public InnerUser() {
        getUserFromContext((InnerUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public InnerUser getInnerUser() {
        return (InnerUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public InnerUser(int id, String username, String password, String firstName,
                     String lastName, String birthday, boolean enabled, boolean locked,
                     boolean expired, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.enabled = enabled;
        this.locked = locked;
        this.expired = expired;
        this.authorities = authorities;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
