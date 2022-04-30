package ir.ebrahimi.phonebook.bootstrap;

import ir.ebrahimi.phonebook.auth.AppUser;
import ir.ebrahimi.phonebook.auth.AppUserRepository;
import ir.ebrahimi.phonebook.auth.AuthGroup;
import ir.ebrahimi.phonebook.auth.AuthGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private AppUserRepository appUserRepository;
    private AuthGroupRepository authGroupRepository;

    @Autowired
    public Bootstrap(AppUserRepository appUserRepository, AuthGroupRepository authGroupRepository) {
        this.appUserRepository = appUserRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        appUserRepository.save(AppUser.builder().username("ali").password("$2a$11$dp4wMyuqYE3KSwIyQmWZFeUb7jCsHAdk7ZhFc0qGw6i5J124imQBi").build());
        appUserRepository.save(AppUser.builder().username("reza").password("$2a$11$dp4wMyuqYE3KSwIyQmWZFeUb7jCsHAdk7ZhFc0qGw6i5J124imQBi").build());

        authGroupRepository.save(AuthGroup.builder().username("ali").authGroup("ADMIN").build());
        authGroupRepository.save(AuthGroup.builder().username("ali").authGroup("USER").build());
        authGroupRepository.save(AuthGroup.builder().username("reza").authGroup("USER").build());
    }
}
