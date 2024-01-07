package com.example.kaspresso

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kaspresso.databinding.TodoItemBinding
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(
    private val onDoneClicked: (Int) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(TodoItemBinding.inflate(LayoutInflater.from(parent.context)), onDoneClicked)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()

    fun addTodo(title: String) {
        items.add(title)
        notifyItemInserted(itemCount + 1)
    }

    fun removeTodo(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class TodoViewHolder(
        val binding: TodoItemBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            with(binding) {
                tvClock.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                tvTitle.text = title
                cbIsDone.setOnClickListener { onClick(layoutPosition) }
                cbIsDone.isChecked = false
            }
        }
    }

}