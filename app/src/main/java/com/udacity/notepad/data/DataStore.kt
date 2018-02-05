package com.udacity.notepad.data

import android.content.Context

import java.util.concurrent.Executor
import java.util.concurrent.Executors

object DataStore {

    val EXEC: Executor = Executors.newSingleThreadExecutor()

    var notes: NoteDatabase? = null
        private set

    fun init(context: Context) {
        notes = NoteDatabase(context)
    }

    fun execute(runnable: Runnable) {
        EXEC.execute(runnable)
    }
}
