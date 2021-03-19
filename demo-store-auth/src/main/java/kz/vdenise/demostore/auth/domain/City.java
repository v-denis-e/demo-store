package kz.vdenise.demostore.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * Entity represents city.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(length = 256)
  @NonNull
  private String name;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;

}
