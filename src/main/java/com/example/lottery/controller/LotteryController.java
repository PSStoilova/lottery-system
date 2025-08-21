package com.example.lottery.controller;

import com.example.lottery.lottery.LotteryService;
import com.example.lottery.lottery.dto.LotteryDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lotteries")
public class LotteryController {

    public final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @PostMapping
    public LotteryDto createLottery(@RequestBody @Valid LotteryDto lotteryDto){
        return lotteryService.createLottery(lotteryDto);
    }

    @GetMapping("/all")
    public List<LotteryDto> fetchAll(){
       return lotteryService.fetchAll();
    }
}
