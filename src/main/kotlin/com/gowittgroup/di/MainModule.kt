package com.gowittgroup.di

import com.gowittgroup.data.MessageDataSource
import com.gowittgroup.data.MessageDataSourceImpl
import com.gowittgroup.room.RoomController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient("mongodb+srv://pawan6186:AKNQzcWBpaYJ3aaW@cluster0.vx89hdm.mongodb.net/?retryWrites=true&w=majority")
            .coroutine
            .getDatabase("message_db")
    }

    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }

    single { RoomController(get()) }
}