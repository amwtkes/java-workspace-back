package com.xiaojin.quoteapi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Quote {
    private String currency;
    private LocalDate localDate;
}
