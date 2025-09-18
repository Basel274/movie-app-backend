package com.movie.auth.services;

import com.movie.auth.entities.RefreshToken;
import com.movie.auth.entities.User;
import com.movie.auth.repository.RefreshTokenRepository;
import com.movie.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(String userName) {

        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("user not found email: " +userName));
        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null) {
            long refreshTokenValidity = 30 * 10000;
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();

            refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {

       RefreshToken refreshToken1 = refreshTokenRepository.findByRefreshToken(refreshToken)
               .orElseThrow(() -> new RuntimeException("refresh token not found"));

       if (refreshToken1.getExpirationTime().compareTo(Instant.now()) < 0){
           refreshTokenRepository.delete(refreshToken1);
           throw new RuntimeException("refresh token expired");
       }

       return refreshToken1;
    }
}
