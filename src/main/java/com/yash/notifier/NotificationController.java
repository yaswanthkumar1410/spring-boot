package com.yash.notifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {


    private MessageService messageService1;
    private MessageService messageService2;

    public NotificationController(@Qualifier("emailService") MessageService service1, @Qualifier("smsService") MessageService service2) {
        this.messageService1 = service1;
        this.messageService2 = service2;
    }

    @GetMapping("/notify/email")
    public String sendEmailNotification() {
        return messageService1.sendNotification();
    }

    @GetMapping("/notify/sms")
    public String sendSmsNotification() {
        return messageService2.sendNotification();
    }
}
