package com.example.task_management_app.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task_management_app.R
import com.example.task_management_app.Task
import com.example.task_management_app.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
kotlinx.android.synthetic.main.fragment_add.view.addTaskName_et

class AddFragment : Fragment() {

    private lateinit var mTaskViewModel:TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {

        val taskName = addTaskName_et.text.toString()
        val taskDesc = addTaskDesc_et.text.toString()
        val priority = addPriority_et.text

        if(inputCheck(taskName, taskDesc, priority)){

            val task = Task(0, taskName, taskDesc, Integer.parseInt(priority.toString()))

            mTaskViewModel.addTask(task)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(taskName: String, taskDesc: String, priority: Editable): Boolean{
        return !(TextUtils.isEmpty(taskName) && TextUtils.isEmpty(taskDesc) && priority.isEmpty())
    }    
}