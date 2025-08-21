package com.example.lottery.lottery;

import com.example.lottery.lottery.data.Lottery;
import com.example.lottery.lottery.data.LotteryRepository;
import com.example.lottery.lottery.dto.LotteryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public LotteryDto createLottery(LotteryDto lotteryDto) {
        Lottery lottery = lotteryDto.toEntity();
        Lottery createdLottery = lotteryRepository.save(lotteryDto.toEntity());
        return LotteryDto.fromEntity(createdLottery);
    }

    public List<LotteryDto> fetchAll() {
        return lotteryRepository.findAll()
                .stream()
                .map(LotteryDto::fromEntity)
                .toList();
    }
}
