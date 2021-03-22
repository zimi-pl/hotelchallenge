package pl.zimi.hotelchallenge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Request {

    private final Integer emptyPremiumRooms;
    private final Integer emptyEconomyRooms;
    private final List<Integer> offers;

    public Request(@JsonProperty("emptyPremiumRooms") final Integer emptyPremiumRooms,
                   @JsonProperty("emptyEconomyRooms") final Integer emptyEconomyRooms,
                   @JsonProperty("offers") final List<Integer> offers) {
        this.emptyPremiumRooms = emptyPremiumRooms;
        this.emptyEconomyRooms = emptyEconomyRooms;
        this.offers = offers;
    }
}
