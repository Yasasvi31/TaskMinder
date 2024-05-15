package com.example.labexam4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.labexam4.databinding.TaskLayoutBinding
import com.example.labexam4.fragments.HomeFragmentDirections
import com.example.labexam4.model.Task

class TaskAdapter:RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(val itemBinding: TaskLayoutBinding):RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object :DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.taskTitle == newItem.taskTitle &&
                    oldItem.taskDate == newItem.taskDate &&
                    oldItem.taskStatus == newItem.taskStatus &&
                    oldItem.taskPriority == newItem.taskPriority &&
                    oldItem.taskDesc == newItem.taskDesc
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = differ.currentList[position]

        holder.itemBinding.taskTitle.text = currentTask.taskTitle
        holder.itemBinding.taskDate.text = currentTask.taskDate
        holder.itemBinding.taskStat.text = currentTask.taskStatus
        holder.itemBinding.taskPri.text = currentTask.taskPriority
        holder.itemBinding.taskDesc.text = currentTask.taskDesc

        holder.itemView.setOnClickListener {
          val direction = HomeFragmentDirections.actionHomeFragmentToEditTaskFragment(currentTask)
          it.findNavController().navigate(direction)
        }



    }
}