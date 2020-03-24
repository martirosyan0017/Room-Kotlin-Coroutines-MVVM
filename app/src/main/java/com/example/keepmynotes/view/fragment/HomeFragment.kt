package com.example.keepmynotes.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepmynotes.interfaces.NoteItemClickListener

import com.example.keepmynote.base.BaseFragment
import com.example.keepmynotes.room.entity.KeepMyNoteEntity
import com.example.keepmynotes.view.adapter.KeepMyNoteAdapter
import com.example.keepmynotes.viewmodel.KeepMyNoteViewModel
import com.example.keepmynotes.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), NoteItemClickListener {

    private lateinit var viewmodel: KeepMyNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        addNoteButton()
        getNotes()
    }

    private fun initViewModel() {
        viewmodel = ViewModelProviders.of(this).get(KeepMyNoteViewModel::class.java)
    }

    private val adapterNote by lazy {
        val notesAdapter = KeepMyNoteAdapter(this)
        recycler_view_notes.layoutManager = LinearLayoutManager(context)
        recycler_view_notes.setHasFixedSize(true)
        recycler_view_notes.adapter = notesAdapter
        return@lazy notesAdapter
    }

    private fun getNotes(){
        viewmodel.getAllNotes()
        val observer = Observer<List<KeepMyNoteEntity>> {
            adapterNote.submitData(it)
        }
        viewmodel.notesLiveDataList?.observe(this,observer)
    }

    private fun addNoteButton() {
        button_add_note.setOnClickListener {
            openFragment(R.id.fragment_container, AddNotesFragment(), null, true)
        }
    }

    override fun onItemClicked(view: View, notes: KeepMyNoteEntity) {
        val bundle = Bundle()
        bundle.putParcelable("LocalKey", notes)
        openFragment(R.id.fragment_container, AddNotesFragment(), bundle, true)
    }
}
