package com.udacity.notepad.recycler

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.udacity.notepad.R
import com.udacity.notepad.data.DataStore
import com.udacity.notepad.data.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.util.*

/**
 * Created by Renan on 04/02/2018.
 */
class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){

    private var notes : List<Note> = ArrayList()
    private var isRefreshing : Boolean = false

    init {
        setHasStableIds(true)
    }
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        refresh()
    }
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.text.text = note.text

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int):NotesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int  = notes.size

    override fun getItemId(position: Int): Long = notes[position].id.toLong()

    fun refresh(){
        if(isRefreshing) return
        isRefreshing = true
        DataStore.execute {
            val notes = DataStore.getNotes().all
            Handler(Looper.getMainLooper()).post {
                this@NotesAdapter.notes = notes
                notifyDataSetChanged()
                isRefreshing = false
            }
        }

    }

    class NotesViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.text
    }

}