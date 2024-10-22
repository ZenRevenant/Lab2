package com.example.testlast

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class BlankFragment2 : Fragment() {

    private val arg:BlankFragment2Args by navArgs()
    private var numb:Int? = null
    private var correctAnswers = 0
    private var totalQuestions = 20
    private var currentQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        numb=arg.number?.toIntOrNull()
        correctAnswers = 0
        totalQuestions = 20
        currentQuestion = 0
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val params = view.layoutParams as ViewGroup.LayoutParams
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        view.layoutParams = params
        val questionText = view.findViewById<TextView>(R.id.question_text)
        val answerInput = view.findViewById<EditText>(R.id.answer_input)
        val submitButton = view.findViewById<Button>(R.id.submit_button)

        generateNewQuestion()

        submitButton.setOnClickListener {
            val answer = answerInput.text.toString().toIntOrNull()
            if (answer != null) {
                checkAnswer(answer, questionText)
                answerInput.text.clear()
            } else {
                Toast.makeText(context, "Введите ответ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateNewQuestion() {
        val questionText = view?.findViewById<TextView>(R.id.question_text)
        if (currentQuestion < totalQuestions) {
            val num1 = numb ?: (2..9).random()
            val num2 = (2..9).random()
            questionText?.text = "$num1 x $num2"
            currentQuestion++
        } else {
            showResult()
        }
    }

    private fun checkAnswer(answer: Int, questionText: TextView) {
        val parts = questionText.text.split(" x ")
        val correctAnswer = parts[0].toInt() * parts[1].toInt()

        if (answer == correctAnswer) {
            correctAnswers++
            Toast.makeText(context, "Правильный ответ", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Неверный ответ", Toast.LENGTH_SHORT).show()
        }

        generateNewQuestion()
    }

    private fun showResult() {
        val percentage = (correctAnswers.toDouble() / totalQuestions) * 100
        val resultMessage = "Вы ответили правильно на $correctAnswers из $totalQuestions вопросов. Процент правильных ответов: ${"%.2f".format(percentage)}%."

        AlertDialog.Builder(requireContext())
            .setTitle("Результат")
            .setMessage(resultMessage)
            .setPositiveButton("ОК") { dialog, _ ->
                dialog.dismiss()
                findNavController().navigateUp() // Возвращаемся к выбору режима
            }
            .show()
    }

    companion object {
        private const val ARG_NUMBER = "number"

        @JvmStatic
        fun newInstance() = BlankFragment2()
    }
}

