package pl.zimi.hotelchallenge.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

    Report reserve(final Request request) {
        final List<Integer> premiumOffers = request.getOffers().stream()
                .filter(price -> price >= 100)
                .sorted(Comparator.reverseOrder())
                .limit(request.getFreePremiumRooms())
                .collect(Collectors.toList());
        final int sumForPremium = premiumOffers.stream().mapToInt(x -> x).sum();

        final List<Integer> economyOffers = request.getOffers().stream()
                .filter(price -> price < 100)
                .sorted(Comparator.reverseOrder())
                .limit(request.getFreeEconomyRooms())
                .collect(Collectors.toList());
        final int sumForEconomy = economyOffers.stream().mapToInt(x -> x).sum();

        return Report.builder()
            .economyIncome(sumForEconomy)
            .usedEconomyRooms(economyOffers.size())
            .premiumIncome(sumForPremium)
            .usedPremiumRooms(premiumOffers.size())
            .build();
    }

}
