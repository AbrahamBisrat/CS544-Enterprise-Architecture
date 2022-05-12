package edu.miu.cs544.cloud.demo.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue
    @Column(name = "actor_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(name = "film_actor",
            joinColumns = { @JoinColumn( name = "actor_id") },
            inverseJoinColumns = { @JoinColumn( name = "film_id") } )
    private Collection<Film> films = new ArrayList<>();

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Actor actor = (Actor) o;
        return id != null && Objects.equals(id, actor.id);
    }

    @Override public int hashCode() {
        return getClass().hashCode();
    }

}
