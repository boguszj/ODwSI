package com.example.odwsi.odwsi.repository.credential;

import com.example.odwsi.odwsi.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, UUID> {

    @Query("select c from Credential c join c.users u where u.username = :username")
    Collection<Credential> findForUserByUsername(String username);

}
