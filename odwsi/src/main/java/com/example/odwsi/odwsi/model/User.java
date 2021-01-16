package com.example.odwsi.odwsi.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.AccessLog;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@Entity
@Table(name = "user", schema = "public")
public class User implements Serializable  {

    public User() {
        this.userId = UUID.randomUUID();
    }

    @Id
    @Column(name = "user_id")
    private final UUID userId;

    @Setter
    @NotNull
    @Column(unique = true)
    private String username;

    @Setter
    @NotNull
    private String password;

    @Setter
    @Transient
    private String passwordConfirmation;

    @ManyToMany(mappedBy = "users")
    private final Collection<Credential> credentials = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private final Collection<AccessLogEntry> accessLogEntries = new ArrayList<>();

    public void addCredential(Credential credential) {
        credentials.add(credential);
    }

    public void removeAccessLogEntry(AccessLogEntry accessLogEntry) {
        accessLogEntries.remove(accessLogEntry);
    }

}
