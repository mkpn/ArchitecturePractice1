package com.example.architecturepractice1.domain

import android.graphics.Bitmap
import com.example.architecturepractice1.domain.message.ImageMessage
import com.example.architecturepractice1.domain.message.Message
import com.example.architecturepractice1.domain.message.TextMessage

class CommonMessageAPI {
    fun fetchAll(ofUserId: Int, completion: (Message?) -> Unit) {
        //...
    }
    fun fetch(id: Int, completion: (Message?) -> Unit) {
        //...
    }
    fun sendTextMessage(text: String, completion: (TextMessage?) -> Unit) {
        //...
    }
    fun sendImageMessage(image: Bitmap, text: String?, completion: (ImageMessage?) -> Unit) {
        //...
    }
}
