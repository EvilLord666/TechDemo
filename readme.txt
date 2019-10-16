################################################### PROJECT DESCRIPTION SECTION ############################################################
Project contains following components and layers
    1) DAL - data access layer containing:
        1.1) Context interface and it Mock implementation, Context is a Collection of IRepository<>
        1.2) Repository interface and it basic implementation
        1.3) Entities - persistent classes (Role, User)
    2) Authorization:
       2.1) Internal basic authorization
       2.2) OAuth2 with internal authorization server
       2.3) OAuth2 with OpenAm authorization server
############################################################################################################################################
########################################################## OAUTH2 TERMS ####################################################################
Resource Owner — an entity that is able to grant access to its protected resources
Authorization Server — grants access tokens to Clients after successfully authenticating Resource Owners and obtaining their authorization
Resource Server — a component that requires an access token to allow, or at least consider, access to its resources
Client — an entity that is capable of obtaining access tokens from authorization servers

Spring:
@EnableResourceServer configures component as entity that can obtain access_token (in my final case it External Identity Server = OpenAm)
@EnableOAuth2Sso makes application an OAuth2 client
############################################################################################################################################
#################################################### INTERNAL OAUTH2 SECTION ###############################################################
Application deploying on localhost:8080, context path is /
Therefore if we try to get resource /api/users we send HTTP GET on localhost:8080/api/users, but without Oauth2 token we getting
401 Response with following JSON:
{
    "error": "unauthorized",
    "error_description": "An Authentication object was not found in the SecurityContext"
}

So if we would like to pass authentication we shold
1) Issue new token using basic authorization:
Send POST localhost:8080/oauth/token
with Basic Authorization (see globalUserDetails, actually it is local stored in AuthorizationService code credentials - mjolnir : 12345678)
header with content-type -  x-www-form-url-encoded (application/x-www-form-urlencoded)
body content is key-value pairs:
             username : root
             password : 123
             grant_type : password

Response is: 200 OK with body
{
    "access_token": "1c9c15d6-302c-4729-826c-257594330396",
    "token_type": "bearer",
    "refresh_token": "fc854cbf-5f57-4011-8697-47eeb8757151",
    "expires_in": 4989,
    "scope": "read write trust"
}

2) Accessing protected resource using access_token (1c9c15d6-302c-4729-826c-257594330396)
with Authorization type Bearer with value = access_token
to localhost:8080/api/users and i am getting test output: {"userId": "0", "userName": "admin"}

###########################################################################################################################################
######################################################## OPEN AM SECTION ##################################################################
OpenAM provides the following three OAuth 2.0 endpoints with the last one, tokeninfo, used for validating tokens:

My OpenAM was deployed on localhost:8899 at context path /OpenAM-14.4.2
Therefore OpenAm base address is: http://localhost:8899/OpenAM-14.4.2

Authorize endpoint: HTTP POST http://localhost:8899/OpenAM-14.4.2/oauth2/authorize?realm=/abcdemo (using for SSO)

First step is to get access token (Realm is abcdemo)

HTTP POST http://localhost:8899/OpenAM-14.4.2/oauth2/access_token?realm=/abcdemo

You'll receive following json:

{
    "access_token": "e8ec0e37-42d4-4df5-b2e5-bfbaefcf6374",
    "refresh_token": "c1ebed79-731e-4ab8-9f03-a8f3ab222e29",
    "scope": "local",
    "token_type": "Bearer",
    "expires_in": 3599
}

For other operation you should use received OAuth2 token as Bearer %token%

HTTP GET http://localhost:8899/OpenAM-14.4.2/oauth2/userinfo?realm=/abcdemo

You'll receive following JSON on userInfo request:
{
    "sub": "mjolnir"
}

###########################################################################################################################################
############################################################## RESOURCES ##################################################################
OAuth protocol description: https://tools.ietf.org/html/rfc6749#section-3
OAuth + spring (use vpn here): https://www.baeldung.com/spring-security-oauth

Spring Configuration: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html

Way of what to switch (use vpn here): https://www.baeldung.com/spring-security-oauth2-enable-resource-server-vs-enable-oauth2-sso
###########################################################################################################################################