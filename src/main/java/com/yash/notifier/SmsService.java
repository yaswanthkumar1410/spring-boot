package com.yash.notifier;

import org.springframework.stereotype.Service;

@Service
public class SmsService implements MessageService {

    public String sendNotification() {
        return "Sms sent!";
    }
}
