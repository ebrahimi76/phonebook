package ir.ebrahimi.phonebook.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserDetailsService implements UserDetailsService{

    private final AppUserRepository appUserRepository;
    private final AuthGroupRepository authGroupRepository;

    public SystemUserDetailsService(AppUserRepository appUserRepository, AuthGroupRepository authGroupRepository){
        super();
        this.appUserRepository = appUserRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findByUsername(username);
        if(null== appUser){
            throw new UsernameNotFoundException("cannot find username: " + username);
        }
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
        return new SystemUserPrincipal(appUser, authGroups);
    }
}
