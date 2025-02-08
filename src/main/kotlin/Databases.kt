package com.today


import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.server.application.*


fun Application.connectToMongoDB(): MongoDatabase {
    val uri = "mongodb+srv://user/pwd@cluster0.jcdjq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
    val mongoClient = MongoClient.create(uri)
    val database = mongoClient.getDatabase("todays_learning")
    monitor.subscribe(ApplicationStopped) {
        mongoClient.close()
    }
    return database
}