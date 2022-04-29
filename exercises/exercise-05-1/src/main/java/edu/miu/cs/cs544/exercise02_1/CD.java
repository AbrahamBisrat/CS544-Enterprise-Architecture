package edu.miu.cs.cs544.exercise02_1;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CD extends Product{

    private String artist;

    public static CD create(String artist) {
        return new CD().builder().artist(artist).build();
    }

}
