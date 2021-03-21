package pl.zimi.hotelchallenge.domain;

import java.util.List;

public class Request {

    private int freePremiumRooms;
    private int freeEconomyRooms;
    private List<Integer> offers;

    public void setFreePremiumRooms(int freePremiumRooms) {
        this.freePremiumRooms = freePremiumRooms;
    }

    public int getFreePremiumRooms() {
        return freePremiumRooms;
    }

    public void setFreeEconomyRooms(int freeEconomyRooms) {
        this.freeEconomyRooms = freeEconomyRooms;
    }

    public int getFreeEconomyRooms() {
        return freeEconomyRooms;
    }

    public void setOffers(List<Integer> offers) {
        this.offers = offers;
    }

    public List<Integer> getOffers() {
        return offers;
    }
}
