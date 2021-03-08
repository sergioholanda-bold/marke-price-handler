package com.santander.subscriber;

import com.santander.service.MarketPriceService;

public class MarketPriceSubscriber {

    private static final MarketPriceSubscriber instance = new MarketPriceSubscriber();
    private final MarketPriceService marketPriceService = MarketPriceService.getInstance();

    private MarketPriceSubscriber() {
    }

    public static MarketPriceSubscriber getInstance() {
        return instance;
    }

    public void onMessage(String message) {
        marketPriceService.persist(message);
    }

}
