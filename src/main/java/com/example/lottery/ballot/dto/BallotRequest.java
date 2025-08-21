package com.example.lottery.ballot.dto;

import jakarta.validation.constraints.NotEmpty;

public record BallotRequest(
        @NotEmpty String email,
        @NotEmpty String lotteryName
) {

}
