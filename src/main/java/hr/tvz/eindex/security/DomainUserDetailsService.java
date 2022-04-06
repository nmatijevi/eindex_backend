package hr.tvz.eindex.security;


import hr.tvz.eindex.user.User;
import hr.tvz.eindex.user.UserRepoJpa;
import hr.tvz.eindex.user.UserRepository;
import hr.tvz.eindex.user.UserRepositoryJdbc;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepoJpa userRepoJpa;

    public DomainUserDetailsService(UserRepoJpa userRepoJpa) {
        this.userRepoJpa = userRepoJpa;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {

        return userRepoJpa
                .findOneByUsername(username)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}

