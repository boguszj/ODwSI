package com.example.odwsi.odwsi.security;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.LoadingCache;

import javax.annotation.Nonnull;

@Service
public class LoginAttemptService {

    private final LoadingCache<String, Integer> loginAttempts;

    public LoginAttemptService() {
        loginAttempts = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(@Nonnull String loginAttemptCacheKey) {
                        return 0;
                    }
                });
    }

    public void loginFailed(String key) {
        try {
            int attempts = loginAttempts.get(key);
            loginAttempts.put(key, attempts + 1);
        } catch (ExecutionException ignored) {
            // ignored
        }
    }

    public boolean isBlocked(String ip) {
        int MAX_ATTEMPTS = 5;
        try {
            return loginAttempts.get(ip) > MAX_ATTEMPTS;
        } catch (ExecutionException exception) {
            return false;
        }
    }

}
