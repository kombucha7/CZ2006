package com.example.cz2006ver2.HomePage

import android.content.ContentValues.TAG
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cz2006ver2.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_todo.view.*

class TodoAdapter(private val todos: ArrayList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(): Boolean{
        var deletedTasks: ArrayList<Todo> = ArrayList()
        for( i in 0 until todos.size)
        {
            if(todos.get(i).isChecked){
                deletedTasks.add(todos.get(i))
            }
        }
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
        return deleteFromDB(deletedTasks)
    }

    private fun deleteFromDB(deletedTasks : ArrayList<Todo>): Boolean{
        if(deletedTasks.size == 0)
            return false
        else{
            val db = FirebaseFirestore.getInstance()
            for( i in 0 until deletedTasks.size){
                var docRef = db.collection("careRecipient").document(deletedTasks.get(i).elderUID)
                    .collection("task").document(deletedTasks.get(i).taskID)
                println("dofref is " + docRef)
                docRef.delete().addOnSuccessListener { task ->
                    Log.w(TAG, "Deleted1111111111")
                }
            }
            return true
        }

    }


    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            tvDeadline.text = curTodo.deadline
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}