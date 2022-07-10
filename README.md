# Spring Boot 2 Login - MVC

This sample demonstrates [flow](https://auth0.com/docs/get-started/authentication-and-authorization-flow/authorization-code-flow)

- Adding authentication with OAuth 2.0 Authorization Server/Auth0 to a Spring Boot 2 MVC application
- Accessing profile information of the authenticated user
- Only allowing authenticated users to access certain resources: OAuth 2.0 protected resource concept

## Pre-requisites

* Java 17, i.e.: IBM Semeru Runtime Open Edition 17.0.3.0

## Configuration

### Callback URLs

When using `tulip` profile, below apply:
* Redirect URL `http://localhost:3000/login/oauth2/code/tulip` is white-listed in the custom `OAuth 2.0 authorization server`, hereby called `tulip`.

When using `auth0` profile, below apply:
* On the [Auth0 Dashboard](https://manage.auth0.com/#/clients) create a new Application of type **Regular Web Application**.
* On the **Settings** tab of your application, add the URL `http://localhost:3000/login/oauth2/code/auth0` to the **Allowed Callback URLs** field. 
* On the **Settings** tab of your application, add the URL `http://localhost:3000/` to the **Allowed Logout URLs** field.

See [here](https://docs.spring.io/spring-security/site/docs/5.2.12.RELEASE/reference/html/oauth2.html#oauth2login-sample-redirect-uri) more details.

### Application configuration

See the application configuration values in:
* `src/main/resources/application-tulip.yml` for `spring.profiles.active=tulip`
* `src/main/resources/application-auth0.yml` for `spring.profiles.active=auth0`

You will have to replace `spring.security.oauth2.client.registration.[registrationId].client-id` and `spring.security.oauth2.client.registration.[registrationId].client-secret`
properties values with the ones you have in your `Auth0 dashboard: Regular Web Application manage` or your own custom `OAuth 2.0 authorization server`.

See [here](https://docs.spring.io/spring-security/site/docs/5.2.12.RELEASE/reference/html/oauth2.html#oauth2login-sample-application-config) more details.

### Running the sample

Open a terminal, go to the project root directory and run the following command:

Linux or MacOS:

```bash
./gradlew bootRun --args='--spring.profiles.active=tulip'
./gradlew bootRun --args='--spring.profiles.active=auth0'
```

The application will be accessible at http://localhost:3000.

## License

This project is licensed under the MIT license. See the [LICENSE](LICENSE) file for more info.

## Concepts

`Tulip` is an Identity Provider platfom (IdP), the source for validating user identity in a federated identity system.
Tulip is built on top of Open Access Management [openam](https://github.com/OpenIdentityPlatform/OpenAM) and is `single-tenant`.

`Auth0` is an Identity Provider platform (IdP) to add sophisticated authentication and authorization to your applications. 
Centralize and manage users from multiple identity providers and give them branded, seamless signup and login experiences.

The `OAuth 2.0` protocol controls authorization to access a protected resource, like: your web app, native app, or API service, through:
* `access token`: the token issued by the authorization server in exchange for the grant (authorization granted to the client by the user/resource owner)
* `refresh token`: an optional token that is exchanged for a new access token, if the access token has expired

`OAuth 2.0` roles:
* `Resource Owner`: Entity that can grant access to a protected resource. Typically, this is the end-user.
* `Client`: Spring Boot web app requesting access to a protected resource: `/profile` on behalf of the `Resource Owner`.
* `Resource Server`: Server hosting the protected resources. This is the API `/profile` we want to access.
* `Authorization Server`: Server that authenticates the `Resource Owner` and issues `Access and ID Tokens` after getting proper authorization. In this case: `Auth0` or `Tulip`.
* `User Agent`: Agent used by the `Resource Owner` to interact with the `Client`, i.e. web browser.

The `OpenID Connect (OIDC)` protocol is built on the `OAuth 2.0` protocol and helps authenticate users and convey information about them.
It's also more opinionated than plain OAuth 2.0, for example in its scope definitions.

The `authorization server` also acts as:
* an OAuth 2.0 token mitigating engine (has a unique issuer URI and its own signing key for tokens)
* OpenID Connect Provider, which means you can request `ID tokens` in addition to `access tokens` 

![ID Token](ID_token.png)
![Token Validation](validate_token.png)
![Decode Token](decode_token.png)

