package pl.zimi.hotelchallenge.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReservationService {

    Report reserve(final Request request) {
        final List<Integer> allPremiumOffers = filterPremiumOffers(request);

        final List<Integer> allEconomyOffers = filterEconomyOffers(request);

        final List<Integer> upgradeOffers = computeUpgradeOffers(request, allPremiumOffers, allEconomyOffers);

        final List<Integer> chosenPremiumOffers = choosePremiumOffers(request, allPremiumOffers, upgradeOffers);

        final List<Integer> chosenEconomyOffers = chooseEconomyOffers(request, allEconomyOffers, upgradeOffers);


        return Report.builder()
            .economyIncome(sum(chosenEconomyOffers))
            .usedEconomyRooms(chosenEconomyOffers.size())
            .premiumIncome(sum(chosenPremiumOffers))
            .usedPremiumRooms(chosenPremiumOffers.size())
            .build();
    }

    private List<Integer> chooseEconomyOffers(Request request, List<Integer> allEconomyOffers, List<Integer> upgradeOffers) {
        final List<Integer> chosenEconomyOffersAll = new ArrayList<>(allEconomyOffers);
        chosenEconomyOffersAll.removeAll(upgradeOffers);

        return chosenEconomyOffersAll.stream()
                .limit(request.getFreeEconomyRooms())
                .collect(Collectors.toList());
    }

    private List<Integer> choosePremiumOffers(Request request, List<Integer> allPremiumOffers, List<Integer> upgradeOffers) {
        return Stream.concat(allPremiumOffers.stream(), upgradeOffers.stream())
                .limit(request.getFreePremiumRooms())
                .collect(Collectors.toList());
    }

    private List<Integer> computeUpgradeOffers(Request request, List<Integer> allPremiumOffers, List<Integer> allEconomyOffers) {
        int leftPremiumRooms = request.getFreePremiumRooms() - allPremiumOffers.size();
        int missingEconomyRooms = allEconomyOffers.size() - request.getFreeEconomyRooms();
        int upgradingRoomsNumber = Math.max(Math.min(leftPremiumRooms, missingEconomyRooms), 0);
        return allEconomyOffers.subList(0, upgradingRoomsNumber);
    }

    private List<Integer> filterEconomyOffers(Request request) {
        return request.getOffers().stream()
                .filter(price -> price < 100)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private List<Integer> filterPremiumOffers(Request request) {
        return request.getOffers().stream()
                .filter(price -> price >= 100)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private int sum(List<Integer> premiumOffers) {
        return premiumOffers.stream().mapToInt(x -> x).sum();
    }

}
