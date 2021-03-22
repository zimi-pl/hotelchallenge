package pl.zimi.hotelchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RoomGroup {

    private final int rooms;
    private final int income;
}
