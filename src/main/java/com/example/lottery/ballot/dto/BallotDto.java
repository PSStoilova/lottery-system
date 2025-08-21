package com.example.lottery.ballot.dto;

import com.example.lottery.ballot.data.Ballot;
import com.example.lottery.users.data.User;

import java.time.Instant;

public record BallotDto(
        String email,
        String username,
        Instant submittedAt,
        String lotteryName
) {
    public static BallotDto fromEntity(Ballot ballot) {
        User user = ballot.getUser();
        return new BallotDto(user.getEmail(), user.getUsername(), ballot.getSubmissionDate(), ballot.getLottery().getName());
    }
}
