package com.example.exercise11_1;

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
