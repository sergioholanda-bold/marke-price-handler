package com.santander.web;

import com.santander.model.Price;
import com.santander.service.MarketPriceService;

/**
 *  Considering that the application is going to use some REST Framework
 */
public class MarketPriceResource {

    private final MarketPriceService marketPriceService = MarketPriceService.getInstance();

    private static final MarketPriceResource instance = new MarketPriceResource();

    private MarketPriceResource() {
    }

    public static MarketPriceResource getInstance() {
        return instance;
    }

    public Price getLatestPrice(String currency) {
        return marketPriceService.getLatestPrice(currency);
    }

}
