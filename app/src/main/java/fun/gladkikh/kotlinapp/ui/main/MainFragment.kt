package `fun`.gladkikh.kotlinapp.ui.main

import `fun`.gladkikh.kotlinapp.R
import `fun`.gladkikh.kotlinapp.data.entity.Note
import `fun`.gladkikh.kotlinapp.ui.activity.MainActivity
import `fun`.gladkikh.kotlinapp.ui.base.BaseFragment
import `fun`.gladkikh.kotlinapp.ui.note.NoteFragment
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.main_fragment.*
import ru.geekbrains.gb_kotlin.ui.main.NotesRVAdapter


class MainFragment : BaseFragment<List<Note>?, MainViewState>() {

    lateinit var adapter: NotesRVAdapter
    override val layoutRes: Int = R.layout.main_fragment
    override val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_notes.layoutManager = GridLayoutManager(activity, 2)
        adapter = NotesRVAdapter {
            val bundle = Bundle()
            bundle.putParcelable(NoteFragment.EXTRA_NOTE, it)
            (activity as MainActivity).getHostNavController()
                .navigate(
                    R.id.noteFragment,
                    bundle
                )

        }

        rv_notes.adapter = adapter

        fab.setOnClickListener {
            (activity as MainActivity).getHostNavController()
                .navigate(
                    R.id.noteFragment
                )
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
        }
    }
}