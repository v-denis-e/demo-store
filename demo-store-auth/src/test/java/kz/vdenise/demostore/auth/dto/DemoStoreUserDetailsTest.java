package kz.vdenise.demostore.auth.dto;

import kz.vdenise.demostore.auth.domain.Role;
import kz.vdenise.demostore.auth.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DemoStoreUserDetailsTest {

  @Test
  void getAuthorities() {
    var user = User.builder()
        .login("test")
        .password("test")
        .lastName("test")
        .build();
    var expectedRole = Role.of("admin");
    user.addRole(expectedRole);

    var authorities = new DemoStoreUserDetails(user).getAuthorities();

    assertThat(authorities).hasSize(1).map(GrantedAuthority::getAuthority).contains("ROLE_" + expectedRole.getName());
  }

}