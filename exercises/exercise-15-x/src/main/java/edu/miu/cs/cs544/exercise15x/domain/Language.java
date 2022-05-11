package edu.miu.cs.cs544.exercise15x.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    @Id
    @Column(name = "language_id")
    @GeneratedValue
    private Long id;

    private String name;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
