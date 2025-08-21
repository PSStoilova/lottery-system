package com.example.lottery.controller;

import com.example.lottery.ballot.BallotService;
import com.example.lottery.ballot.dto.BallotDto;
import com.example.lottery.ballot.dto.BallotRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/ballots")
public class BallotController {
    private final BallotService ballotService;

    public BallotController(BallotService ballotService) {
        this.ballotService = ballotService;
    }

    @PostMapping
    public BallotDto registerBallot(@RequestBody BallotRequest ballotRequest) {
        return ballotService.registerBallot(ballotRequest);
    }

    @GetMapping("/all")
    public List<BallotDto> getBallots(@RequestParam("email") String email) {
        return ballotService.fetchAllBallotsPerUser(email);
    }

    @GetMapping("/day")
    public List<BallotDto> getBallots(@RequestParam("email") String email, @RequestParam("date") LocalDate date) {
        return ballotService.fetchBallots(email, date);
    }

}
