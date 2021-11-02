package com.xiaojin.quoteapi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ExchangeRate {
    private ServiceLoader<ExchangeProvider> loader;

    public ExchangeRate() {
        this.loader = ServiceLoader.load(ExchangeProvider.class);
    }

    public Iterator<ExchangeProvider> getProviders(boolean refresh) {
        if (refresh) {
            loader.reload();
        }
        return loader.iterator();
    }
}
