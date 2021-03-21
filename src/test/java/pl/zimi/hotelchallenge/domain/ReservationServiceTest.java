package pl.zimi.hotelchallenge.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ReservationServiceTest {

    final List<Integer> CLIENT_OFFERS = Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209);
    final ReservationService reservationService = new ReservationService();

    @Test
    void fullOccupation() {
        final Request request = basicRequest(3, 3);
        final Report report = reservationService.reserve(request);

        Assertions.assertThat(report.getUsedPremiumRooms()).isEqualTo(3);
        Assertions.assertThat(report.getPremiumIncome()).isEqualTo(738);
        Assertions.assertThat(report.getUsedEconomyRooms()).isEqualTo(3);
        Assertions.assertThat(report.getEconomyIncome()).isEqualTo(167);
    }

    @Test
    void someRoomsLeft() {
        final Request request = basicRequest(7, 5);
        final Report report = reservationService.reserve(request);

        Assertions.assertThat(report.getUsedPremiumRooms()).isEqualTo(6);
        Assertions.assertThat(report.getPremiumIncome()).isEqualTo(1054);
        Assertions.assertThat(report.getUsedEconomyRooms()).isEqualTo(4);
        Assertions.assertThat(report.getEconomyIncome()).isEqualTo(189);
    }

    @Test
    void economyRoomsLeft() {
        final Request request = basicRequest(2, 7);
        final Report report = reservationService.reserve(request);

        Assertions.assertThat(report.getUsedPremiumRooms()).isEqualTo(2);
        Assertions.assertThat(report.getPremiumIncome()).isEqualTo(583);
        Assertions.assertThat(report.getUsedEconomyRooms()).isEqualTo(4);
        Assertions.assertThat(report.getEconomyIncome()).isEqualTo(189);
    }

    @Test
    void enforceUpgradeWhenOtherwiseHotelIsOutOfRoom() {
        final Request request = basicRequest(7, 1);
        final Report report = reservationService.reserve(request);

        Assertions.assertThat(report.getUsedPremiumRooms()).isEqualTo(7);
        Assertions.assertThat(report.getPremiumIncome()).isEqualTo(1153);
        Assertions.assertThat(report.getUsedEconomyRooms()).isEqualTo(1);
        Assertions.assertThat(report.getEconomyIncome()).isEqualTo(45);
    }

    private Request basicRequest(int premiumRoomsNumber, int economyRoomsNumber) {
        return Request.builder()
                .freePremiumRooms(premiumRoomsNumber)
                .freeEconomyRooms(economyRoomsNumber)
                .offers(CLIENT_OFFERS)
                .build();
    }


}