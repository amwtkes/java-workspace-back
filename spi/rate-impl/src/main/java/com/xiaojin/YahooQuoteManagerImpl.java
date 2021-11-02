package com.xiaojin;

import com.xiaojin.quoteapi.Quote;
import com.xiaojin.quoteapi.QuoteManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class YahooQuoteManagerImpl implements QuoteManager {
    @Override
    public List<Quote> getQuotes(String baseCurrency, LocalDate localDate) {
        List<Quote> rets = new ArrayList<>(2);
        rets.add(new Quote("CN", LocalDate.now()));
        rets.add(new Quote("US", LocalDate.now()));
        return rets;
    }
}
