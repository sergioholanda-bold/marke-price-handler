package com.santander.resource;

import com.santander.model.Price;
import com.santander.service.MarketPriceService;
import com.santander.subscriber.MarketPriceSubscriber;

/**
 *  Considering that the application uses some REST Framework
 */
//@RestController
//@RequestMapping("/api")
public class MarketPriceResource {

    private final MarketPriceService marketPriceService = MarketPriceService.getInstance();

    private static final MarketPriceResource instance = new MarketPriceResource();

    public static MarketPriceResource getInstance() {
        return instance;
    }

    //@Get()
    public Price getLatestPrice(String currency) {
        return marketPriceService.getLatestPrice(currency);
    }

}
