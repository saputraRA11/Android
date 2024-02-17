package com.example.myreadwritefile

import android.content.Context

internal object FileHelper {
    fun writeToFile(fileModel:FIleModel,context:Context) {
        context.openFileOutput(fileModel.filename,Context.MODE_PRIVATE).use {
            it.write(fileModel.data?.toByteArray())
        }
    }

    fun readFromFile(context: Context,filename:String):FIleModel {
        val fileModel = FIleModel()
        fileModel.filename = filename
        fileModel.data = context.openFileInput(filename).bufferedReader().useLines {
            lines ->
            lines.fold("") {
                some,text ->
                "$some\n$text"
            }
        }

        return fileModel
    }


}