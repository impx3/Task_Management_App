package com.example.task_management_app.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_management_app.R
import com.example.task_management_app.model.Task
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    //Date part
    //val currentDate = Date()
    //val dateString = DateUtils.dateToString(currentDate)
    //val parsedDate = DateUtils.stringToDate(dateString)

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
       return taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.taskName_txt.text = currentItem.taskName
        holder.itemView.taskDesc_txt.text = currentItem.taskDesc
        holder.itemView.priority_txt.text = currentItem.priority.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }

}    