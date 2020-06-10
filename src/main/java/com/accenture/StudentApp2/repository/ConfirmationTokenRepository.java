package com.accenture.StudentApp2.repository;

import com.accenture.StudentApp2.emailConfirmation.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<VerificationToken, String> {

    VerificationToken findByConfirmationToken(String confirmationToken);
}
