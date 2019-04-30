package com.wildanka.websocketmvvm.model.entity;

public class MarketData {
    private String lastPrice;
    private String priceChange;
    private String lowestPrice;
    private String highestPrice;
    private String volume;
    private String volumeIDR;

    public MarketData(String lastPrice, String priceChange, String lowestPrice, String highestPrice, String volume, String volumeIDR) {
        this.lastPrice = lastPrice;
        this.priceChange = priceChange;
        this.lowestPrice = lowestPrice;
        this.highestPrice = highestPrice;
        this.volume = volume;
        this.volumeIDR = volumeIDR;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(String highestPrice) {
        this.highestPrice = highestPrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolumeIDR() {
        return volumeIDR;
    }

    public void setVolumeIDR(String volumeIDR) {
        this.volumeIDR = volumeIDR;
    }
}
