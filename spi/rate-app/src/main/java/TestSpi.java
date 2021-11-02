import com.xiaojin.quoteapi.ExchangeProvider;
import com.xiaojin.quoteapi.ExchangeRate;

import java.util.Iterator;

public class TestSpi {
    public static void main(String[] args) {
        ExchangeRate exchangeRate = new ExchangeRate();
        Iterator<ExchangeProvider> providers = exchangeRate.getProviders(true);
        while (providers.hasNext()) {
            System.out.println(providers.next().getClass().getName());
        }
    }
}
