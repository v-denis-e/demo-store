package kz.vdenise.demostore.auth.service;

import kz.vdenise.demostore.auth.dto.DemoStoreUserDetails;
import kz.vdenise.demostore.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service which loads user details by username.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DemoStoreUserDetailsService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = repository.findByLogin(username)
        .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found!"));
    return new DemoStoreUserDetails(user);
  }

}
