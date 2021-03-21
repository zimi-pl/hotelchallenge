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

        final List<Integer> economyOffers = request.getOffers().stream()
                .filter(price -> price < 100)
                .sorted(Comparator.reverseOrder())
                .limit(request.getFreeEconomyRooms())
                .collect(Collectors.toList());

        return Report.builder()
            .economyIncome(sum(economyOffers))
            .usedEconomyRooms(economyOffers.size())
            .premiumIncome(sum(premiumOffers))
            .usedPremiumRooms(premiumOffers.size())
            .build();
    }

    private int sum(List<Integer> premiumOffers) {
        return premiumOffers.stream().mapToInt(x -> x).sum();
    }

}
