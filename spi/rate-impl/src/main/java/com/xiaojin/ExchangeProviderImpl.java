package com.xiaojin;

import com.xiaojin.quoteapi.ExchangeProvider;
import com.xiaojin.quoteapi.QuoteManager;

public class ExchangeProviderImpl implements ExchangeProvider {
    @Override
    public QuoteManager create() {
        return new YahooQuoteManagerImpl();
    }
}
