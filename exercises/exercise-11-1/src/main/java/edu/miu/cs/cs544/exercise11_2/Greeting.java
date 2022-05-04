package edu.miu.cs.cs544.exercise11_2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Greeting {

    private String greeting;

    public void sayHello() {
        System.out.println(greeting);
    }

}
