package com.example.architecturepractice1.domain

import android.graphics.Bitmap

data class ImageMessageInputValidator(
    val image: Bitmap?,
    val text: String?
) {
    val isValid: Boolean
        get() {
            if (image == null) {
                return false
            }
            if (text != null && text.length > 80) {
                return false
            }
            return true
        }
}