package com.example.library.user.token;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class ConfirmationTokenPurge {

    @Autowired
    private final ConfirmationTokenService confirmationTokenService;

    private static final String midnightScheduledFormat = "0 0 0 * * *";

    @Scheduled(cron = midnightScheduledFormat)
    public void purgeExpiredTokensDaily(){
        confirmationTokenService.deleteExpiredTokens();
    }
}
