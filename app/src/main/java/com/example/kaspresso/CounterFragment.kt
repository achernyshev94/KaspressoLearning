package com.example.kaspresso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.kaspresso.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {

    private val counter = MutableLiveData(0)
    private lateinit var binding : FragmentCounterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentCounterBinding.inflate(inflater).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        counter.observe(viewLifecycleOwner) { binding.tvCounter.text = "Counter: $it" }
    }

    private fun initClickListeners() {
        with(binding) {
            btnIncreaseCounter.setOnClickListener { increaseCounter() }
            btnDecreaseCounter.setOnClickListener { decreaseCounter() }
            btnOpenTodoScreen.setOnClickListener { openTodoList() }
            btnOpenLoginScreen.setOnClickListener { openLoginScreen() }
        }
    }

    private fun openLoginScreen() {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(android.R.id.content, LoginFragment.newInstance())
            .commit()
    }

    private fun openTodoList() {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(android.R.id.content, TodoFragment())
            .commit()
    }

    private fun increaseCounter() {
        counter.postValue(counter.value?.plus(1))
    }

    private fun decreaseCounter() {
        if (counter.value != 0) {
            counter.postValue(counter.value?.minus(1))
        } else {
            Toast.makeText(context, "Can't count lower than 0", Toast.LENGTH_LONG).apply {

            }.show()
        }
    }
}