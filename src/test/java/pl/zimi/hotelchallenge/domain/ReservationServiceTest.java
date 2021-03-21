package pl.zimi.hotelchallenge.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    final List<Integer> clientOffers = Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209);
    final ReservationService reservationService = new ReservationService();

    @Test
    void fullOccupation() {
        final Request request = new Request();
        request.setFreePremiumRooms(3);
        request.setFreeEconomyRooms(3);
        request.setOffers(clientOffers);
        final Report report = reservationService.reserve(request);

        Assertions.assertThat(report.getUsedPremiumRooms()).isEqualTo(3);
        Assertions.assertThat(report.getPremiumIncome()).isEqualTo(738);
        Assertions.assertThat(report.getUsedEconomyRooms()).isEqualTo(3);
        Assertions.assertThat(report.getEconomyIncome()).isEqualTo(167);
    }


}