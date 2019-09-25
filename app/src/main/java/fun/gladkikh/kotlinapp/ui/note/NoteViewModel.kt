package `fun`.gladkikh.kotlinapp.ui.note

import `fun`.gladkikh.kotlinapp.data.NotesRepository
import `fun`.gladkikh.kotlinapp.data.entity.Note
import `fun`.gladkikh.kotlinapp.data.model.NoteResult
import `fun`.gladkikh.kotlinapp.ui.base.BaseViewModel
import androidx.lifecycle.Observer


class NoteViewModel : BaseViewModel<Note?, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            NotesRepository.saveNote(pendingNote!!)
        }
    }

    fun loadNote(noteId: String) {
        NotesRepository.getNoteById(noteId).observeForever(Observer<NoteResult> {
            if (it == null) return@Observer

            when (it) {
                is NoteResult.Success<*> -> viewStateLiveData.value = NoteViewState(note = it.data as? Note)
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = it.error)
            }
        })
    }
}