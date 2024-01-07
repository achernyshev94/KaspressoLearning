package com.example.kaspresso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kaspresso.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    lateinit var binding: FragmentTodoBinding

    private val todoAdapter = TodoAdapter(::onTodoDoneClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTodoBinding.inflate(layoutInflater).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.btnSubmit.setOnClickListener { onAddTodoClick() }
    }

    private fun initList() {
        binding.rvTodo.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun onTodoDoneClick(position: Int) {
        todoAdapter.removeTodo(position)
    }

    private fun onAddTodoClick() {
        binding.etTodo.text.toString().takeIf { it.isNotBlank() }?.let(todoAdapter::addTodo)
    }
}
