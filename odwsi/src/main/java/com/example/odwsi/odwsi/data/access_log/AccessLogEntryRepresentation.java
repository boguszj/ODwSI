package com.example.odwsi.odwsi.data.access_log;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Valid
@Data
@Builder
public class AccessLogEntryRepresentation implements Comparable {

    @NotNull
    Instant loginTime;
    @NotNull
    String ip;

    @Override
    public int compareTo(Object o) {
        if (o instanceof AccessLogEntryRepresentation) {
            return loginTime.compareTo(((AccessLogEntryRepresentation) o).loginTime);
        } else {
            return 0;
        }
    }
}
