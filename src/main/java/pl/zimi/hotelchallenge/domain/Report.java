package pl.zimi.hotelchallenge.domain;

public class Report {
    private int usedPremiumRooms;
    private int premiumIncome;
    private int usedEconomyRooms;
    private int economyIncome;

    public int getUsedPremiumRooms() {
        return usedPremiumRooms;
    }

    public void setUsedPremiumRooms(int usedPremiumRooms) {
        this.usedPremiumRooms = usedPremiumRooms;
    }

    public int getPremiumIncome() {
        return premiumIncome;
    }

    public void setPremiumIncome(int premiumIncome) {
        this.premiumIncome = premiumIncome;
    }

    public int getUsedEconomyRooms() {
        return usedEconomyRooms;
    }

    public void setUsedEconomyRooms(int usedEconomyRooms) {
        this.usedEconomyRooms = usedEconomyRooms;
    }

    public int getEconomyIncome() {
        return economyIncome;
    }

    public void setEconomyIncome(int economyIncome) {
        this.economyIncome = economyIncome;
    }
}
