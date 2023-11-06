package com.gowittgroup.plugins

import com.gowittgroup.room.RoomController
import com.gowittgroup.routes.chatSocket
import com.gowittgroup.routes.getAllMessage
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
   install(Routing){
       val roomController by inject<RoomController>()
       chatSocket(roomController = roomController)
       getAllMessage(roomController)
   }
}
