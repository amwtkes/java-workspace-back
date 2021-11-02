package com.xiaojin.quoteapi;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Quote {
    private String currency;
    private LocalDate localDate;
}
