package edu.miu.cs.cs544.exercise05_1;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DVD extends Product{

    private String genre;

    public static DVD create(String genre, String artist, String description) { // take more vars, and use super
        DVD dvd = new DVD();
        dvd.setGenre(genre);
        dvd.setName(artist);
        dvd.setDescription(description);
        return dvd;
    }

}
