package com.santander.model;

import java.time.LocalDateTime;

public class Price {

    private Long id;
    private String instrument;
    private Double bid;
    private Double ask;
    private LocalDateTime timestamp;

    public Price(Long id, String instrument, Double bid, Double ask, LocalDateTime timestamp) {
        this.id = id;
        this.instrument = instrument;
        this.bid = bid;
        this.ask = ask;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return "ID: " + this.id +
                "\nInstrument: " + this.instrument +
                "\nBid: " + this.bid +
                "\nAsk: " + this.ask +
                "\nTimestamp: " + this.timestamp;
    }

}
