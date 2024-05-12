package com.example.task_management_app.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.task_management_app.R
import com.example.task_management_app.Task
import com.example.task_management_app.TaskViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.updateTaskName_et.setText(args.currentTask.taskName)
        view.updateTaskDesc_et.setText(args.currentTask.taskDesc)
        view.updatePriority_et.setText(args.currentTask.priority.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val taskName = updateTaskName_et.text.toString()
        val taskDesc = updateTaskDesc_et.text.toString()
        val priority = Integer.parseInt(updatePriority_et.text.toString())

        if(inputCheck(taskName,taskDesc,updatePriority_et.text)){
            val updatedTask = Task(args.currentTask.id, taskName, taskDesc, priority)
            mTaskViewModel.updateTask(updatedTask)

            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }    

    private fun inputCheck(taskName: String, taskDesc: String, priority: Editable): Boolean{
        return !(TextUtils.isEmpty(taskName) && TextUtils.isEmpty(taskDesc) && priority.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTaskViewModel.deleteTask(args.currentTask)
            Toast.makeText(
                requireContext(),
                "Successfully deleted: ${args.currentTask.taskName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentTask.taskName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentTask.taskName}?")
        builder.create().show()
    }
    }

}    