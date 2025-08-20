package com.example.lottery.ballot;

import com.example.lottery.ballot.data.Ballot;
import com.example.lottery.ballot.data.BallotRepository;
import com.example.lottery.ballot.dto.BallotDto;
import com.example.lottery.ballot.dto.BallotRequest;
import com.example.lottery.users.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallotService {

    private final BallotRepository ballotRepository;
    private final UserRepository userRepository;


    public BallotService(BallotRepository ballotRepository, UserRepository userRepository) {
        this.ballotRepository = ballotRepository;
        this.userRepository = userRepository;
    }

    public BallotDto registerBallot(BallotRequest request) {
        Ballot ballot = new Ballot(
                userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("User's email was not found."))
        );
        ballotRepository.save(ballot);
        return BallotDto.fromEntity(ballot);
    }

    public List<BallotDto> fetchAllBallotsPerUser(String email){
       return ballotRepository
               .findAllByUser_Email(email)
               .stream()
               .map(BallotDto::fromEntity)
               .toList();
    }
}
