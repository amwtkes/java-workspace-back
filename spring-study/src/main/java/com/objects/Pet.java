package com.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet {
    private String name;
    private String nickName;
    private Integer age;
}
