/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.util.StringUtils;


public class AccessResponseDto {
    
    
    /*AccessResponseDto(String accessToken, String tokenType, int expiresIn, String refreshToken, String scope) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public OAuth2AccessToken.TokenType getTokenType() {
        return OAuth2AccessToken.TokenType.BEARER.getValue().equalsIgnoreCase(tokenType) ? OAuth2AccessToken.TokenType.BEARER : null;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public Set<String> getScopes() {
        return StringUtils.isEmpty(scope) ? Collections.emptySet() : Stream.of(scope.split("\\s+")).collect(Collectors.toSet());
    }
    
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    private String scope;*/
}
