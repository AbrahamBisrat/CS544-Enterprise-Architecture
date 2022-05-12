package edu.miu.cs544.cloud.client.client.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Actor {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDateTime lastUpdate;

}
