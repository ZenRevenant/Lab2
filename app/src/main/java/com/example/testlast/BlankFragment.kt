package com.example.testlast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController


class BlankFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        val buttonALL = view.findViewById<Button>(R.id.all_numbers_button)
        val buttonSEL = view.findViewById<Button>(R.id.selective_button)
        buttonALL.setOnClickListener {
            openExerciseFragment(null)
        }
        buttonSEL.setOnClickListener {
            val numberInput = view.findViewById<EditText>(R.id.number_input)
            val number = numberInput.text.toString().toIntOrNull()
            if (number != null && number in 2..9) {
                openExerciseFragment(number)
            } else {
                Toast.makeText(context, "Введите число от 2 до 9", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment StartFragment.
         */
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
    private fun openExerciseFragment(number: Int?) {
        val action = BlankFragmentDirections.actionBlankFragmentToBlankFragment2(number.toString())
        view?.findNavController()?.navigate(action)
    }
}