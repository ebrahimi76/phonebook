package ir.ebrahimi.phonebook.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class SystemUserPrincipal implements UserDetails {

    private AppUser appUser;
    private List<AuthGroup> authGroups;

    public SystemUserPrincipal(AppUser appUser, List<AuthGroup> authGroups) {
        super();
        this.appUser = appUser;
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null == authGroups) {
            return Collections.emptySet();
        }

        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        authGroups.forEach(group -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
        });
        return grantedAuthorities;

    }

    @Override
    public String getPassword() {
        return this.appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
