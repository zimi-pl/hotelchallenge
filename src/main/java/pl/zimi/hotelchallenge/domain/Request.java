package pl.zimi.hotelchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class Request {

    private final int freePremiumRooms;
    private final int freeEconomyRooms;
    private final List<Integer> offers;

}
