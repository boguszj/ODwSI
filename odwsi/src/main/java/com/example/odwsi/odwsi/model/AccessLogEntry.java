package com.example.odwsi.odwsi.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "access_log")
public class AccessLogEntry {

    public AccessLogEntry(User user, String ip, Instant loginTime) {
        this.user = user;
        this.accessLogEntryId = UUID.randomUUID();
        this.ip = ip;
        this.loginTime = loginTime;
    }

    @Id
    @Column(name = "access_log_entry_id")
    private UUID accessLogEntryId;

    private String ip;

    private Instant loginTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
