package `fun`.gladkikh.kotlinapp.ui.main

import `fun`.gladkikh.kotlinapp.data.entity.Note
import `fun`.gladkikh.kotlinapp.ui.base.BaseViewState


class MainViewState(val notes: List<Note>? = null,
                    error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)