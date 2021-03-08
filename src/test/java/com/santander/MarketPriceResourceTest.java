package com.santander;

import com.santander.model.Price;
import com.santander.resource.MarketPriceResource;
import com.santander.subscriber.MarketPriceSubscriber;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple MarketPriceService.
 */
public class MarketPriceResourceTest {

    private final MarketPriceSubscriber marketPriceSubscriber = MarketPriceSubscriber.getInstance();
    private final MarketPriceResource marketPriceResource = MarketPriceResource.getInstance();

    List<String> inputs = Arrays.asList(
        "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001",
        "107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002",
        "108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002",
        "109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100",
        "110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110"
    );

    @Before
    public void init() {
        inputs.forEach(marketPriceSubscriber::onMessage);
    }

    @Test
    public void shouldRetrieveLatestPriceEUR_USD() {
        Price price = marketPriceResource.getLatestPrice("EUR/USD");

        assertEquals(price.getId(), Long.valueOf(106));
        assertEquals(price.getInstrument(), "EUR/USD");
        //Values
        assertEquals(price.getBid(), Double.valueOf("1.099"));
        assertEquals(price.getAsk(), Double.valueOf("1.201"));
        //Timestamp
        assertEquals(price.getTimestamp().getDayOfMonth(), 1);
        assertEquals(price.getTimestamp().getMonth(), Month.JUNE);
        assertEquals(price.getTimestamp().getYear(), 2020);
        assertEquals(price.getTimestamp().getHour(), 12);
        assertEquals(price.getTimestamp().getMinute(), 1);
        assertEquals(price.getTimestamp().getSecond(), 1);
    }

    @Test
    public void shouldRetrieveLatestPriceEUR_JPY() {
        Price price = marketPriceResource.getLatestPrice("EUR/JPY");

        assertEquals(price.getId(), Long.valueOf(110));
        assertEquals(price.getInstrument(), "EUR/JPY");
        //Values
        assertEquals(price.getBid(), Double.valueOf("119.49"));
        assertEquals(price.getAsk(), Double.valueOf("120.03"));
        //Timestamp
        assertEquals(price.getTimestamp().getDayOfMonth(), 1);
        assertEquals(price.getTimestamp().getMonth(), Month.JUNE);
        assertEquals(price.getTimestamp().getYear(), 2020);
        assertEquals(price.getTimestamp().getHour(), 12);
        assertEquals(price.getTimestamp().getMinute(), 1);
        assertEquals(price.getTimestamp().getSecond(), 2);
    }

    @Test
    public void shouldRetrieveLatestPriceGBP_USD() {
        Price price = marketPriceResource.getLatestPrice("GBP/USD");

        assertEquals(price.getId(), Long.valueOf(109));
        assertEquals(price.getInstrument(), "GBP/USD");
        //Values
        assertEquals(price.getBid(), Double.valueOf("1.249"));
        assertEquals(price.getAsk(), Double.valueOf("1.257"));
        //Timestamp
        assertEquals(price.getTimestamp().getDayOfMonth(), 1);
        assertEquals(price.getTimestamp().getMonth(), Month.JUNE);
        assertEquals(price.getTimestamp().getYear(), 2020);
        assertEquals(price.getTimestamp().getHour(), 12);
        assertEquals(price.getTimestamp().getMinute(), 1);
        assertEquals(price.getTimestamp().getSecond(), 2);
    }

}
