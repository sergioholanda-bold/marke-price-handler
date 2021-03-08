package com.santander.service;

import com.santander.model.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MarketPriceService {

    private static final MarketPriceService instance = new MarketPriceService();

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
    private List<Price> savedPrices = new ArrayList<>();

    private MarketPriceService() {
    }

    public static MarketPriceService getInstance() {
        return instance;
    }

    /**
     *  With an incoming price, process each with a margin (add commission) function,
     * assume it is simply -0.1% on bid, +0.1% on ask (subtract from bid, add to ask).
     *
     * @param priceFeed
     * @return
     */
    public void persist(String priceFeed) {
        String[] fields = priceFeed.split(",");

        if (fields.length != 5) {
            throw new RuntimeException();
        }

        Long id = Long.valueOf(fields[0].trim());
        String instrument = fields[1].trim();
        Double bid = subtractCommission(Double.valueOf(fields[2].trim()));
        Double ask = addCommission(Double.valueOf(fields[3].trim()));
        LocalDateTime timestamp = LocalDateTime.parse(fields[4].trim(), TIMESTAMP_FORMATTER);

        savedPrices.add(new Price(id, instrument, bid, ask, timestamp));
    }

    private Double addCommission(Double value) {
        Double increaseValue = value + (value * 0.001);
        return precision(increaseValue);
    }

    private Double subtractCommission(Double value) {
        Double decreaseValue = value - (value * 0.001);
        return precision(decreaseValue);
    }

    private Double precision(Double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_EVEN);
        return bigDecimal.doubleValue();
    }

    public Price getLatestPrice(String currency) {
        Optional<Price> optionalPrice = savedPrices.stream()
                .filter(price -> price.getInstrument().equals(currency))
                .max(Comparator.comparing(Price::getTimestamp));

        return optionalPrice.orElseThrow(RuntimeException::new);
    }

}
