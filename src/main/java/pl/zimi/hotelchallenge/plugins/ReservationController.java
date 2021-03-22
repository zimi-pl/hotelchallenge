package pl.zimi.hotelchallenge.plugins;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.zimi.hotelchallenge.domain.Report;
import pl.zimi.hotelchallenge.domain.Request;
import pl.zimi.hotelchallenge.domain.ReservationService;
import reactor.core.publisher.Mono;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reserve")
    Mono<Report> reserve(@RequestBody final Request request) {
        return Mono.just(reservationService.reserve(request));
    }

}
