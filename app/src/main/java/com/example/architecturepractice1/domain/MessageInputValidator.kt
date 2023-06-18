package com.example.architecturepractice1.domain

import android.graphics.Bitmap
import com.example.architecturepractice1.domain.message.MessageType

class MessageInputValidator(
    val messageType: MessageType,
    val image: Bitmap?,
    val text: String?
) {
    val isValid: Boolean
        get() = isTextValid && isImageValid

    private val isTextValid: Boolean
        get() = when (messageType) {
            MessageType.TEXT -> text != null && text.length <= 300
            MessageType.IMAGE -> text == null || text.length <= 80
            MessageType.OFFICIAL -> false
        }

    private val isImageValid: Boolean
        get() = image != null
}