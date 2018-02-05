package com.udacity.notepad.data

import java.util.*

/**
 * Created by Renan on 04/02/2018.
 */

class Note{
    var id : Int
    var text : String?
    var isPinned :Boolean
    var createdAt :Date
    var updatedAt :Date?

    init {
         id  = -1
         text = null
         isPinned = false
         createdAt = Date()
         updatedAt = null
    }
}