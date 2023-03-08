package org.exercise2;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    NotificationService notificationService;

    public UserService(NotificationService service) {
        this.notificationService = service;
    }
}
