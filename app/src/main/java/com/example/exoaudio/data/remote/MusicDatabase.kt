package com.example.exoaudio.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.example.exoaudio.data.entities.Song
import kotlinx.coroutines.tasks.await

class MusicDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val booksCollection = firestore.collection("books")

    suspend fun getAllSongs(): List<Song> {
        return try {
            val response = booksCollection.get().await().toObjects(Song::class.java)
            response.sortBy { it.mediaId.toInt() }
            response
        } catch(e: Exception) {
            emptyList()
        }
    }
}