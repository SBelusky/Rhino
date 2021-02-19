package com.samuell.rhino.auth;

import com.google.common.collect.Lists;
import com.samuell.rhino.security.ApplicationUserRole;
import com.samuell.rhino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("fake")
public class FakeApplicationUserDAOService implements ApplicationUserDAO{

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public FakeApplicationUserDAOService(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){

        List<ApplicationUser> applicationUsers =
                userService.getAllUsersForUserDetails()
                .stream()
                .map(user -> {
                    ApplicationUser applicationUser = new ApplicationUser(
                            ApplicationUserRole.valueOf(user.getRole().toUpperCase()).getGrantedAuthorities(),
                            passwordEncoder.encode(user.getPassword()),
                            user.getUsername(),
                            true,
                            true,
                            true,
                            !user.isWas_deleted()
                    );
                    return applicationUser;
                })
                .collect(Collectors.toList());

        return applicationUsers;
    }
}
