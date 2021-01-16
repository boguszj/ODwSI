package com.example.odwsi.odwsi.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "credential")
public class Credential implements Serializable {

    @Builder
    public Credential(String name, String owner) {
        this.credentialId = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
    }

    @Id
    @Column(name = "credential_id")
    private UUID credentialId;

    private String name;

    private String owner;

    @ManyToMany
    @JoinTable(
            name = "user__credential",
            joinColumns = @JoinColumn(name = "credential_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private final Collection<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public boolean isOwner(String username) {
        return username.equals(owner);
    }

}
