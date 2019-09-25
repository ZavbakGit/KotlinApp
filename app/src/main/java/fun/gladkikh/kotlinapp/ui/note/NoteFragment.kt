package `fun`.gladkikh.kotlinapp.ui.note

import `fun`.gladkikh.kotlinapp.R
import `fun`.gladkikh.kotlinapp.data.entity.Note
import `fun`.gladkikh.kotlinapp.ui.activity.MainActivity
import `fun`.gladkikh.kotlinapp.ui.base.BaseFragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_note1.*
import kotlinx.android.synthetic.main.fragment_note1.et_body
import kotlinx.android.synthetic.main.fragment_note1.et_title
import kotlinx.android.synthetic.main.note_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class NoteFragment : BaseFragment<Note?, NoteViewState>() {


    companion object {
        val EXTRA_NOTE = NoteFragment::class.java.name + "extra.NOTE"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"
    }

    private var note: Note? = null
    override val layoutRes = R.layout.note_fragment
    override val viewModel: NoteViewModel by lazy {
        ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val noteId = arguments?.getParcelable<Note>(EXTRA_NOTE)?.id

        noteId?.let {
            viewModel.loadNote(it)
        } ?: let {
            getString(R.string.new_note_title)
        }

    }


    override fun renderData(data: Note?) {
        this.note = data
        (activity as MainActivity).setTitleActionBar(note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(note!!.lastChanged)
        } ?: getString(R.string.new_note_title))

        initView()
    }

    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private fun initView() {
        if (note != null) {
            et_title.setText(note!!.title)
            et_body.setText(note!!.text)
            val color = when (note!!.color) {
                Note.Color.WHITE -> R.color.white
                Note.Color.YELLOW -> R.color.yellow
                Note.Color.GREEN -> R.color.green
                Note.Color.BLUE -> R.color.blue
                Note.Color.RED -> R.color.red
                Note.Color.VIOLET -> R.color.violet
                Note.Color.PINK -> R.color.pink
            }
        }

        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }

    private fun saveNote() {
        if (et_title.text == null || (et_title.text?.length ?: 0) < 3)
            return

        note = note?.copy(
            title = et_title.text.toString(),
            text = et_body.text.toString(),
            lastChanged = Date()
        ) ?: Note(
            UUID.randomUUID().toString(),
            et_title.text.toString(),
            et_body.text.toString()
        )

        note?.let { viewModel.save(it) }
    }

}