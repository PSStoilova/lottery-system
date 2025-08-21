package com.example.lottery.lottery.dto;

import com.example.lottery.lottery.data.Lottery;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public record LotteryDto(
        String name
) {

    public Lottery toEntity() {
        return new Lottery(name, List.of(), tomorrowAtMidnight());
    }

    public static LotteryDto fromEntity(Lottery lottery){
        return new LotteryDto(lottery.getName());
    }

    private Instant tomorrowAtMidnight() {
        ZoneId zone = ZoneId.systemDefault();
        LocalDate tomorrow = LocalDate.now(zone).plusDays(1);
        LocalDateTime tomorrowMidnight = tomorrow.atStartOfDay();
        return tomorrowMidnight.atZone(zone).toInstant();
    }
}
