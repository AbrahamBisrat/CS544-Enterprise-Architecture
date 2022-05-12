package edu.miu.cs544.cloud.demo.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue
    @Column(name = "language_id")
    private Long id;

    private String name;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
