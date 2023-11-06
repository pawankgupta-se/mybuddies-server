package com.gowittgroup

import com.gowittgroup.di.mainModule
import com.gowittgroup.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    install(Koin) {
        modules(mainModule)
    }
    configureSockets()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()


}


