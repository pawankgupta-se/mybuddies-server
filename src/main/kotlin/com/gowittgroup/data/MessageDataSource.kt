package com.gowittgroup.data

import com.gowittgroup.data.model.Message

interface MessageDataSource {
    suspend fun getAllMessage(): List<Message>
    suspend fun insertMessage(message: Message)
}