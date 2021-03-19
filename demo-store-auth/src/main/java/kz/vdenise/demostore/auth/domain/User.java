package kz.vdenise.demostore.auth.domain;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * Representing user entity.
 */
@Entity
@EqualsAndHashCode(exclude = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String login;
  @NonNull
  private String password;
  private String firstName;
  @NonNull
  private String lastName;
  private LocalDate birtDate;
  private String email;
  private String phoneNumber;
  private String address;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;
  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  @ManyToMany
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

}
