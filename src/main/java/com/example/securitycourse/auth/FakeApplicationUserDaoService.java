package com.example.securitycourse.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.securitycourse.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = List.of(
                new ApplicationUser(
                        "annasmith",
                        passwordEncoder.encode("password"),
                        STUDENT.getGrantedAuthorities(),
                        true,true,true,true)
                ,
                new ApplicationUser(
                        "linda",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,true,true,true)
                ,
                new ApplicationUser(
                        "tom",
                        passwordEncoder.encode("password"),
                        ADMINTRAINEE.getGrantedAuthorities(),
                        true,true,true,true)
        );

        return applicationUsers;
    }
}
