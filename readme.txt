OpenAM provides the following three OAuth 2.0 endpoints with the last one, tokeninfo, used for validating tokens:

/oauth2/authorize
Authorization endpoint defined in RFC 6749, used to obtain an authorization grant from the resource owner

Example: https://openam.example.com:8443/openam/oauth2/authorize

/oauth2/access_token
Token endpoint defined in RFC 6749, used to obtain an access token from the authorization server

Example: https://openam.example.com:8443/openam/oauth2/access_token

/oauth2/tokeninfo
Endpoint not defined in RFC 6749, used to validate tokens, and to retrieve information such as scopes

Given an access token, a resource server can perform an HTTP GET on /oauth2/tokeninfo?access_token=token-id to retrieve a JSON object indicating token_type, expires_in, scope, and the access_token ID.

Example: https://openam.example.com:8443/openam/oauth2/tokeninfo
