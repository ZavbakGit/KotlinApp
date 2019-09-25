package `fun`.gladkikh.kotlinapp.ui.main

import `fun`.gladkikh.kotlinapp.data.NotesRepository
import `fun`.gladkikh.kotlinapp.data.entity.Note
import `fun`.gladkikh.kotlinapp.data.model.NoteResult
import `fun`.gladkikh.kotlinapp.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


class MainViewModel : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = Observer<NoteResult> {
        if (it == null) return@Observer

        when (it) {
            is NoteResult.Success<*> -> {
                viewStateLiveData.value = MainViewState(notes = it.data as? List<Note>)
            }
            is NoteResult.Error -> {
                viewStateLiveData.value = MainViewState(error = it.error)
            }
        }
    }

    private val repositoryNotes = NotesRepository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }


    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}