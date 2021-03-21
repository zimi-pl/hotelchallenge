package pl.zimi.hotelchallenge.domain;

import java.util.Comparator;

public class ReservationService {

    Report reserve(final Request request) {
        final int sumForPremium = request.getOffers().stream()
                .filter(price -> price >= 100)
                .sorted(Comparator.reverseOrder())
                .limit(request.getFreePremiumRooms()).mapToInt(x -> x).sum();

        final int sumForEconomy = request.getOffers().stream()
                .filter(price -> price < 100)
                .sorted(Comparator.reverseOrder())
                .limit(request.getFreeEconomyRooms()).mapToInt(x -> x).sum();

        return Report.builder()
            .economyIncome(sumForEconomy)
            .usedEconomyRooms(request.getFreeEconomyRooms())
            .premiumIncome(sumForPremium)
            .usedPremiumRooms(request.getFreePremiumRooms())
            .build();
    }

}
