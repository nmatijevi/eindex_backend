package hr.tvz.eindex.security;


import hr.tvz.eindex.user.User;
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

    private final UserRepositoryJdbc userRepositoryJdbc;

    public DomainUserDetailsService(UserRepositoryJdbc userRepositoryJdbc) {
        this.userRepositoryJdbc = userRepositoryJdbc;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {

        return userRepositoryJdbc
                .findUserByEmail(email)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User with this " + email + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}

