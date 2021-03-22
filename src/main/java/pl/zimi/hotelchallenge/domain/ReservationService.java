package pl.zimi.hotelchallenge.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReservationService {

    Report reserve(final Request request) {
        final List<Integer> premiumOffers = request.getOffers().stream()
                .filter(price -> price >= 100)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        final List<Integer> economyOffers = request.getOffers().stream()
                .filter(price -> price < 100)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        int leftPremiumRooms = request.getFreePremiumRooms() - premiumOffers.size();
        int missingEconomyRooms = economyOffers.size() - request.getFreeEconomyRooms();
        int upgradingRoomsNumber = Math.max(Math.min(leftPremiumRooms, missingEconomyRooms), 0);
        final List<Integer> upgradeOffers = economyOffers.subList(0, upgradingRoomsNumber);

        final List<Integer> chosenPremiumOffers = Stream.concat(premiumOffers.stream(), upgradeOffers.stream())
                .limit(request.getFreePremiumRooms())
                .collect(Collectors.toList());

        final List<Integer> chosenEconomyOffersAll = new ArrayList<>(economyOffers);
        chosenEconomyOffersAll.removeAll(upgradeOffers);

        final List<Integer> chosenEconomyOffers = chosenEconomyOffersAll.stream().limit(request.getFreeEconomyRooms()).collect(Collectors.toList());


        return Report.builder()
            .economyIncome(sum(chosenEconomyOffers))
            .usedEconomyRooms(chosenEconomyOffers.size())
            .premiumIncome(sum(chosenPremiumOffers))
            .usedPremiumRooms(chosenPremiumOffers.size())
            .build();
    }

    private int sum(List<Integer> premiumOffers) {
        return premiumOffers.stream().mapToInt(x -> x).sum();
    }

}
