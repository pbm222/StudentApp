package com.accenture.StudentApp2.eventHandler;

import com.accenture.StudentApp2.emailConfirmation.EmailSenderService;
import com.accenture.StudentApp2.userModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(User.class)
public class UserEventHandler {

    @Autowired
    private EmailSenderService emailSenderService;

    @HandleAfterCreate
    public void handleUserSave(User user){

        emailSenderService.sendEmail(user);
    }


}
