package com.example.lottery;

import com.example.lottery.lottery.LotteryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LotteryScheduler {
    private final LotteryService lotteryService;

    public LotteryScheduler(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "UTC")
    public void runLotteryJob() {

        lotteryService.processWinningLotteries();
    }
}
