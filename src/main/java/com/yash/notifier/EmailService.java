package com.yash.notifier;

import org.springframework.stereotype.Service;

@Service
public class EmailService implements MessageService {

    public String sendNotification() {
        return "Email sent!";
    }
}
