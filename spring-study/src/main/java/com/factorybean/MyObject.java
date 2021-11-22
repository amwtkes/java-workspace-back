package com.factorybean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyObject {
    private String objectName;
    private Integer objectAge;
}
