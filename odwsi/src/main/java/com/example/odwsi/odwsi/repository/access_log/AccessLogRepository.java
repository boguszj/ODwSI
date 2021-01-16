package com.example.odwsi.odwsi.repository.access_log;

import com.example.odwsi.odwsi.model.AccessLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccessLogRepository extends JpaRepository<AccessLogEntry, UUID> {

    Optional<AccessLogEntry> findFirstByUserUserIdOrderByLoginTime(UUID userId);

}
