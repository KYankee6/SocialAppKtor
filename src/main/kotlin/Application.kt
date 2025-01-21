package com.uicheon

import com.uicheon.dao.DatabaseFactory
import com.uicheon.di.configureDI
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()
    configureDI()
    configureSecurity()
    configureSerialization()
    configureRouting()
}
