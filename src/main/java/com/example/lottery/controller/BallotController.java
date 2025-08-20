package com.example.lottery.controller;

import com.example.lottery.ballot.BallotService;
import com.example.lottery.ballot.dto.BallotDto;
import com.example.lottery.ballot.dto.BallotRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ballots")
public class BallotController {
    private final BallotService ballotService;

    public BallotController(BallotService ballotService) {
        this.ballotService = ballotService;
    }

    @PostMapping
    public BallotDto registerBallot(@RequestBody BallotRequest ballotRequest){
        return ballotService.registerBallot(ballotRequest);
    }

}
