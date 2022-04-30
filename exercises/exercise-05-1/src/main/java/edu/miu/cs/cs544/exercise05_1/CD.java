package edu.miu.cs.cs544.exercise05_1;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CD extends Product{

    private String artist;

    public static CD create(String artist, String name, String desc) {
        CD cd = new CD();
        cd.setName(name);
        cd.setArtist(artist);
        cd.setDescription(desc);

        return cd;
    }

}
