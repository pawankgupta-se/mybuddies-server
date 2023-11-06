package com.gowittgroup.data
import org.litote.kmongo.coroutine.CoroutineDatabase
import com.gowittgroup.data.model.Message

class MessageDataSourceImpl(private val database: CoroutineDatabase): MessageDataSource {
    private val messages = database.getCollection<Message>()
    override suspend fun getAllMessage(): List<Message> {
        return messages.find().descendingSort(Message::timestamp)
            .toList()
    }

    override suspend fun insertMessage(message: Message) {
        messages.insertOne(message)
    }
}