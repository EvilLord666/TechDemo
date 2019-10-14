/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.techdemo.security.client;

import java.util.Set;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import ru.techdemo.dto.AccessResponseDto;

public class OpenAmOAuth2Client implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

    public OpenAmOAuth2Client(RestOperations restOperations){
        this.restOperations = restOperations;
    }
    
    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration = authorizationGrantRequest.getClientRegistration();

        String tokenUri = clientRegistration.getProviderDetails().getTokenUri();

        MultiValueMap<String, String> tokenRequest = new LinkedMultiValueMap<String, String>();
        tokenRequest.add("client_id", clientRegistration.getClientId());
        tokenRequest.add("client_secret", clientRegistration.getClientSecret());
        tokenRequest.add("grant_type", clientRegistration.getAuthorizationGrantType().getValue());
        tokenRequest.add("code", authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getCode());
        tokenRequest.add("redirect_uri", authorizationGrantRequest.getAuthorizationExchange().getAuthorizationRequest().getRedirectUri());
        tokenRequest.add("scope", String.join(" ", authorizationGrantRequest.getClientRegistration().getScopes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // headers.add(HttpHeaders.USER_AGENT, DISCORD_BOT_USER_AGENT);

        ResponseEntity<AccessResponseDto> response =
            restOperations.exchange(tokenUri, HttpMethod.POST, new HttpEntity<>(tokenRequest, headers), AccessResponseDto.class);

        AccessResponseDto accessResponse = response.getBody();

        Set<String> scopes = accessResponse.getScopes().isEmpty() ?
            authorizationGrantRequest.getAuthorizationExchange().getAuthorizationRequest().getScopes() : accessResponse.getScopes();

        return OAuth2AccessTokenResponse.withToken(accessResponse.getAccessToken())
            .tokenType(accessResponse.getTokenType())
            .expiresIn(accessResponse.getExpiresIn())
            .scopes(scopes)
            .build();
    }
    
    private final RestOperations restOperations;
}
