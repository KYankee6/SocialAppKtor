package com.uicheon

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.uicheon.model.AuthResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

const val jwtAudience = "jwt-audience"
const val jwtDomain = "https://jwt-provider-domain/"
const val jwtRealm = "ktor sample app"
const val jwtSecret = "secret"

private const val CLAIM = "email"
fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim(CLAIM).asString() != null) {
                    JWTPrincipal(payload = credential.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = AuthResponse(
                        errorMessage = "Token is not valid or has expired"
                    )
                )
            }
        }
    }
}

fun generateToken(email: String): String {
    return JWT.create()
        .withAudience(jwtAudience)
        .withIssuer(jwtDomain)
        .withClaim(CLAIM, email)
        //.withExpiresAt()
        .sign(Algorithm.HMAC256(jwtSecret))
}