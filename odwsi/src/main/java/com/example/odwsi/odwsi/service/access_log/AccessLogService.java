package com.example.odwsi.odwsi.service.access_log;

import com.example.odwsi.odwsi.data.access_log.AccessLogEntryRepresentation;
import com.example.odwsi.odwsi.model.AccessLogEntry;
import com.example.odwsi.odwsi.model.User;
import com.example.odwsi.odwsi.repository.access_log.AccessLogRepository;
import com.example.odwsi.odwsi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessLogService {

    private final UserService userService;
    private final AccessLogRepository accessLogRepository;

    @Transactional
    public void registerLogin(String ip) {
        int MAX_ACCESS_LOG_SIZE = 100;
        User user = userService.getCurrentUser();
        if (user.getAccessLogEntries().size() >= MAX_ACCESS_LOG_SIZE) {
            handleAccessLogOverflow(user);
        }
        AccessLogEntry accessLogEntry = new AccessLogEntry(user, ip, Instant.now());
        accessLogRepository.save(accessLogEntry);
    }

    private void handleAccessLogOverflow(User user) {
        Optional<AccessLogEntry> lastEntry = accessLogRepository.findFirstByUserUserIdOrderByLoginTime(user.getUserId());
        lastEntry.ifPresent(entry -> {
            user.removeAccessLogEntry(entry);
            accessLogRepository.delete(entry);
        });
    }

    public Collection<AccessLogEntryRepresentation> getAccessLogEntries() {
        User user = userService.getCurrentUser();
        List<AccessLogEntryRepresentation> representations = user.getAccessLogEntries().stream()
                .map(entry -> AccessLogEntryRepresentation.builder()
                        .ip(entry.getIp())
                        .loginTime(entry.getLoginTime())
                        .build()
                )
                .sorted()
                .collect(Collectors.toList());
        Collections.reverse(representations);
        return representations;
    }

}
