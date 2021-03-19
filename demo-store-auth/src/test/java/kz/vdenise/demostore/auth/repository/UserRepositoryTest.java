package kz.vdenise.demostore.auth.repository;

import kz.vdenise.demostore.auth.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

  @Autowired
  UserRepository repository;

  @Test
  void findByLogin() {
    var expected = repository.save(User.builder().login("test").password("test").lastName("test").build());
    repository.save(User.builder().login("test2").password("test2").lastName("test2").build());

    var actual = repository.findByLogin(expected.getLogin());

    assertThat(actual).isPresent();
    assertThat(actual.get()).isEqualTo(expected);
  }

  @AfterEach
  void tearDown() {
    repository.deleteAll();
  }
}