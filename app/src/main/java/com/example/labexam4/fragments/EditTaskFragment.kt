package com.example.labexam4.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.labexam4.MainActivity
import com.example.labexam4.R
import com.example.labexam4.databinding.FragmentEditTaskBinding
import com.example.labexam4.model.Task
import com.example.labexam4.viewmodel.TaskViewModel


class EditTaskFragment : Fragment(R.layout.fragment_edit_task),MenuProvider {

    private var editTaskBinding:FragmentEditTaskBinding? = null
    private val binding get() = editTaskBinding!!

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var currentTask:Task

    private val args:EditTaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editTaskBinding = FragmentEditTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        taskViewModel = (activity as MainActivity).taskViewModel
        currentTask = args.task!!

        binding.editTaskTitle.setText(currentTask.taskTitle)
        binding.editTaskDate.setText(currentTask.taskDate)
        binding.editTaskStatus.setText(currentTask.taskStatus)
        binding.editTaskPriority.setText(currentTask.taskPriority)
        binding.editNoteDesc.setText(currentTask.taskDesc)

        binding.editTaskFab.setOnClickListener{
            val taskTitle = binding.editTaskTitle.text.toString().trim()
            val taskDate = binding.editTaskDate.text.toString().trim()
            val taskStat = binding.editTaskStatus.text.toString().trim()
            val taskPri = binding.editTaskPriority.text.toString().trim()
            val taskDesc = binding.editNoteDesc.text.toString().trim()

           if (taskTitle.isNotEmpty()){
               val task = Task(currentTask.id,taskTitle,taskDate,taskStat,taskPri,taskDesc)
               taskViewModel.updateTask(task)
               view.findNavController().popBackStack(R.id.homeFragment,false)
           }else{
               Toast.makeText(context,"Please enter task title",Toast.LENGTH_SHORT).show()
           }


        }


    }

    private fun deleteTask(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Task")
            setMessage("Do you want to delete this task?")
            setPositiveButton("Delete"){_,_ ->
                taskViewModel.deleteTask(currentTask)
                Toast.makeText(context,"Task Deleted",Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)

            }
            setNegativeButton("Cancel",null)
                }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_task,menu)
    }

    override fun onMenuItemSelected(menuItem:MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu -> {
                deleteTask()
                true
            }else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editTaskBinding = null
    }


}