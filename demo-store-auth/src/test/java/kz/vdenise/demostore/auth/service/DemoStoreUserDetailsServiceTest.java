package kz.vdenise.demostore.auth.service;

import kz.vdenise.demostore.auth.domain.User;
import kz.vdenise.demostore.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DemoStoreUserDetailsServiceTest {

  @Mock
  UserRepository userRepository;
  @InjectMocks
  DemoStoreUserDetailsService service;

  @Test
  void loadUserByUsername() {
    var expected = User.builder()
        .login("test")
        .password("test")
        .lastName("test")
        .build();
    given(userRepository.findByLogin(expected.getLogin()))
        .willReturn(Optional.of(expected));

    var actual = service.loadUserByUsername(expected.getLogin());

    assertThat(actual.getUsername()).isEqualTo(expected.getLogin());
  }

  @Test
  void loadUserByUsername_userNotFound() {
    var username = "unknown";
    given(userRepository.findByLogin(username))
        .willReturn(Optional.empty());

    assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(username));
  }
}