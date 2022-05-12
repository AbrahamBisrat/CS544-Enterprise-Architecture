package edu.miu.cs.cs544.exercise15x.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime lastUpdate;

}
