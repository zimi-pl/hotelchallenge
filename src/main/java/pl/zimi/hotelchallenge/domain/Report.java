package pl.zimi.hotelchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Report {
    private final int usedPremiumRooms;
    private final int premiumIncome;
    private final int usedEconomyRooms;
    private final int economyIncome;
}
