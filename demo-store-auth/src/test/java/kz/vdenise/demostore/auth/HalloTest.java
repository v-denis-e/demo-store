package kz.vdenise.demostore.auth;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HalloTest {

  @Test
  void hallo() {
    assertThat("Hallo, " + "world!").isEqualTo("Hallo, world!");
  }

}
