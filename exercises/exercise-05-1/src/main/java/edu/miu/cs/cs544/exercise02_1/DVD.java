package edu.miu.cs.cs544.exercise02_1;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DVD extends Product{

    private String genre;

    public static DVD create(String genre) {
        return new DVD()
                .builder()
                .genre(genre)
                .build();
    }

}
