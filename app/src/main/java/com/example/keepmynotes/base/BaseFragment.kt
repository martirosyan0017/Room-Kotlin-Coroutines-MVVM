package com.example.keepmynote.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.keepmynotes.room.entity.KeepMyNoteEntity
import com.example.keepmynotes.view.fragment.HomeFragment
import com.example.keepmynotes.viewmodel.KeepMyNoteViewModel
import com.example.keepmynotes.R
import com.example.keepmynotes.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseFragment : Fragment(), CoroutineScope{

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var job: Job
    private var dialog: AlertDialog? = null
    private lateinit var viewmodelDelete: KeepMyNoteViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun initViewModel (){
        viewmodelDelete = ViewModelProviders.of(this).get(KeepMyNoteViewModel::class.java)
    }

    fun openFragment(resId: Int, fragment: Fragment, bundle: Bundle?, updateScreen: Boolean) {
        val parentActivity: BaseActivity? = activity as BaseActivity
        parentActivity?.createFragment(resId, fragment, bundle, updateScreen)
    }

    fun showDeleteDialog(title: String, message: String, note: KeepMyNoteEntity) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton("Yes") { _, _ ->
            lifecycleScope.launch(Dispatchers.Main) {
                viewmodelDelete.deleteSingleNote(note)
                    openFragment(R.id.fragment_container, HomeFragment(), null, true)

            }
        }
         builder.setNegativeButton("No") { _, _ ->
        }
        dialog = builder.create()
        dialog!!.show()
    }
}
