package com.example.keepmynotes.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.keepmynote.base.BaseFragment
import com.example.keepmynotes.room.entity.KeepMyNoteEntity
import com.example.keepmynotes.viewmodel.KeepMyNoteViewModel
import com.example.keepmynotes.R
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.tool_bar_add_notes_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNotesFragment : BaseFragment() {

    private var note: KeepMyNoteEntity? = null
    private lateinit var roomViewModel: KeepMyNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        onClick()
        arguments?.let {
            note = it.getParcelable("LocalKey")
            edit_text_title.setText(note?.title)
            edit_text_note.setText(note?.note)
        }
        saveNote()
    }

    private fun initViewModel() {
        roomViewModel = ViewModelProviders.of(this).get(KeepMyNoteViewModel::class.java)
    }

    private fun saveNote() {
        button_save.setOnClickListener {
            val noteTitle = edit_text_title.text.toString().trim()
            val noteBody = edit_text_note.text.toString().trim()

            if (noteBody.isEmpty()) {
                edit_text_note.error = "Note required"
                edit_text_note.requestFocus()
                return@setOnClickListener
            }
                lifecycleScope.launch(Dispatchers.Main) {
                    val mNote = KeepMyNoteEntity(noteTitle, noteBody)
                        if (note == null) {
                            roomViewModel.saveSingleNote(mNote)
                        } else {
                            mNote.id = note!!.id
                            roomViewModel.updateSingleNote(mNote)
                    }
                    openFragment(R.id.fragment_container, HomeFragment(), null, true)
            }
        }
    }

    private fun onClick() {
        back_button.setOnClickListener {
            openFragment(R.id.fragment_container, HomeFragment(), null, true)
        }
        favorite_button.setOnClickListener {
            Toast.makeText(context, "Favorite", Toast.LENGTH_LONG).show()
        }
        share_button.setOnClickListener {
            Toast.makeText(context, "Share", Toast.LENGTH_LONG).show()
        }
        delete_button.setOnClickListener {
            showDeleteDialog(
                "Are you sure you want delete this note?",
                "You cannot undo this operation!",
                note!!
            )
        }
    }
}
