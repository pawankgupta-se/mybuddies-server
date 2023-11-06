package com.gowittgroup.room

import com.gowittgroup.data.MessageDataSource
import com.gowittgroup.data.model.Message
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class RoomController(private val messageDataSource: MessageDataSource) {
    private val members = ConcurrentHashMap<String, Member>()

    fun onJoin(username: String, sessionId: String, socket: WebSocketSession){
        if(members.containsKey(username)) throw MemberAlreadyExistsException()
        members[username] = Member(username = username, sessionId = sessionId, socket = socket)
    }

    suspend fun sendMessage(sendUsername: String, message: String){
        members.values.forEach{ member ->
            val messageEntity = Message(text = message, username = sendUsername, timestamp = System.currentTimeMillis())
            messageDataSource.insertMessage(messageEntity)
            val parsedMessage = Json.encodeToString(messageEntity)
            member.socket.send(Frame.Text(parsedMessage))
        }
    }
    suspend fun getAllMessage(): List<Message>{
        return messageDataSource.getAllMessage()
    }

    suspend fun tryDisconnect(username:String){
        members[username]?.socket?.close()
        if(members.containsKey(username)){
            members.remove(username)
        }
    }
}