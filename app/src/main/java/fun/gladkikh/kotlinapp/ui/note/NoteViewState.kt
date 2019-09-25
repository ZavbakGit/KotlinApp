package `fun`.gladkikh.kotlinapp.ui.note

import `fun`.gladkikh.kotlinapp.data.entity.Note
import `fun`.gladkikh.kotlinapp.ui.base.BaseViewState


class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)