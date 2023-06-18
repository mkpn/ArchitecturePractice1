package com.example.architecturepractice1.domain

import android.graphics.Bitmap
import com.example.architecturepractice1.domain.message.Message
import com.example.architecturepractice1.domain.message.MessageType

class MessageSender(private val api: CommonMessageAPI, messageType: MessageType) {
    var delegate: MessageSenderDelegate? = null

    val messageType: MessageType = messageType
    var text: String? = null
        set(value) {
            field = value
            if (!isTextValid) {
                delegate?.notifyInvalid()
            }
        }

    var image: Bitmap? = null
        set(value) {
            field = value
            if (!isImageValid) {
                delegate?.notifyInvalid()
            }
        }

    var isLoading: Boolean = false
        private set

    var result: Message? = null
        private set

    private val isTextValid: Boolean
        get() = when (messageType) {
            MessageType.TEXT -> text != null && text!!.length <= 300
            MessageType.IMAGE -> text == null || text!!.length <= 80
            MessageType.OFFICIAL -> false
        }

    private val isImageValid: Boolean
        get() = image != null

    private fun isValid(
        messageType: MessageType,
        image: Bitmap?,
        text: String?
    ): Boolean {
        val messageInputValidator = MessageInputValidator(messageType, image, text)
        return messageInputValidator.isValid
    }

    fun send() {
        if (!isValid(messageType, image, text)) {
            delegate?.notifyInvalid()
            return
        }

        isLoading = true

        when (messageType) {
            MessageType.TEXT -> {
                api.sendTextMessage(text!!) { message ->
                    isLoading = false
                    result = message
                    delegate?.notifyCompletion()
                }
            }
            MessageType.IMAGE -> {
                api.sendImageMessage(image!!, text) { message ->
                    // Handle completion for image message
                }
            }
            MessageType.OFFICIAL -> {
                // Handle official message (not implemented)
                throw UnsupportedOperationException("OfficialMessage is not supported.")
            }
        }
    }
}
