package edu.miu.cs544.cloud.demo.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue
    @Column(name = "film_id")
    private Long id;

    private String title;

    private String description;

    @Column(name = "release_year")
    private Integer year;

//    @OneToOne
//    @JoinColumn(name = "language_id")
//    private Language language;

//    @OneToOne
//    @JoinColumn(name = "original_language_id")
//    private Language originalLanguage;

//    @Column(name = "rental_duration")
//    private Integer rentalDuration;
//
//    @Column(name = "rental_rate")
//    private float rentalRate;
//
//    private Integer length;
//
//    @Column(name = "replacement_cost")
//    private float replacementCost;
//
//    private String rating;
//
//    @Column(name = "special_features")
//    private String specialFeatures;
//
//    @Column(name = "last_update")
//    private LocalDateTime lastUpdate;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "films")
//    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Actor> actors = new ArrayList<>();

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Film film = (Film) o;
        return id != null && Objects.equals(id, film.id);
    }

    @Override public int hashCode() {
        return getClass().hashCode();
    }

}
