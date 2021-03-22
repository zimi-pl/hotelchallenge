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
            .economy(makeGroup(chosenEconomyOffers))
            .premium(makeGroup(chosenPremiumOffers))
            .build();
    }

    private RoomGroup makeGroup(final List<Integer> chosenPremiumOffers) {
        return RoomGroup.builder()
                .rooms(chosenPremiumOffers.size())
                .income(sum(chosenPremiumOffers))
                .build();
    }

    private List<Integer> chooseEconomyOffers(final Request request, final List<Integer> allEconomyOffers, final List<Integer> upgradeOffers) {
        final List<Integer> chosenEconomyOffersAll = new ArrayList<>(allEconomyOffers);
        chosenEconomyOffersAll.removeAll(upgradeOffers);

        return chosenEconomyOffersAll.stream()
                .limit(request.getEmptyEconomyRooms())
                .collect(Collectors.toList());
    }

    private List<Integer> choosePremiumOffers(final Request request, final List<Integer> allPremiumOffers, final List<Integer> upgradeOffers) {
        return Stream.concat(allPremiumOffers.stream(), upgradeOffers.stream())
                .limit(request.getEmptyPremiumRooms())
                .collect(Collectors.toList());
    }

    private List<Integer> computeUpgradeOffers(final Request request, final List<Integer> allPremiumOffers, final List<Integer> allEconomyOffers) {
        int leftPremiumRooms = request.getEmptyPremiumRooms() - allPremiumOffers.size();
        int missingEconomyRooms = allEconomyOffers.size() - request.getEmptyEconomyRooms();
        int upgradingRoomsNumber = Math.max(Math.min(leftPremiumRooms, missingEconomyRooms), 0);
        return allEconomyOffers.subList(0, upgradingRoomsNumber);
    }

    private List<Integer> filterEconomyOffers(final Request request) {
        return request.getOffers().stream()
                .filter(price -> price < 100)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private List<Integer> filterPremiumOffers(final Request request) {
        return request.getOffers().stream()
                .filter(price -> price >= 100)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private int sum(final List<Integer> premiumOffers) {
        return premiumOffers.stream().mapToInt(x -> x).sum();
    }

}
