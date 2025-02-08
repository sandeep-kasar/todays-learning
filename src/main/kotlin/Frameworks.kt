package com.today

import com.today.domain.ports.TimeTableRepository
import com.today.infrastructure.TimetableRepositoryImpl
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            single<TimeTableRepository> { TimetableRepositoryImpl(mongoDatabase = connectToMongoDB()) }
        })
    }
}
