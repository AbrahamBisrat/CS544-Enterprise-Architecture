package edu.miu.cs544.cloud.demo.server.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ActorDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDateTime lastUpdate;

}
