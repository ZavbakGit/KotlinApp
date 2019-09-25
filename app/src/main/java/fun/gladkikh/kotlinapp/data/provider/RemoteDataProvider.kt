package `fun`.gladkikh.kotlinapp.data.provider

import `fun`.gladkikh.kotlinapp.data.entity.Note
import `fun`.gladkikh.kotlinapp.data.model.NoteResult
import androidx.lifecycle.LiveData


interface RemoteDataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
}