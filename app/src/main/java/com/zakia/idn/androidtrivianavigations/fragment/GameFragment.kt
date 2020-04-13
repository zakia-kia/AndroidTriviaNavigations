package com.zakia.idn.androidtrivianavigations.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.zakia.idn.androidtrivianavigations.Question

import com.zakia.idn.androidtrivianavigations.R
import com.zakia.idn.androidtrivianavigations.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "What is Android Jetpack?",
            answers = listOf("All of these", "Tools", "Documentation", "Libraries")
        ),
        Question(
            text = "What is the base class for layouts?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        Question(
            text = "What layout do you use for complex screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        Question(
            text = "What do you use to push structured data into a layout?",
            answers = listOf("Data binding", "Data pushing", "Set text", "An OnClick method")
        ),
        Question(
            text = "What method do you use to inflate layouts in fragments?",
            answers = listOf(
                "onCreateView()",
                "onActivityCreated()",
                "onCreateLayout()",
                "onInflateLayout()"
            )
        )
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numberQuestion = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
        randomiQuiz()
        binding.game = this
        binding.btnSubmit.setOnClickListener { view: View ->
            val checkedId = binding.rgGame.checkedRadioButtonId
            if (-1 != checkedId) {
                var answerPosition = 0
                when (checkedId) {
                    R.id.rb_second_answer -> answerPosition = 1
                    R.id.rb_third_answer -> answerPosition = 2
                    R.id.rb_fourth_answer -> answerPosition = 3
                }
                if (answers[answerPosition] == currentQuestion.answers[0]) {
                    questionIndex++
                    if (questionIndex < numberQuestion) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        view.findNavController().navigate(
                            R.id
                                .action_gameFragment_to_wonFragment
                        )
                    }
                } else {
                    view.findNavController().navigate(
                        R.id
                            .action_gameFragment_to_gameOverFragment
                    )
                }
            }
        }
        return binding.root
    }

    private fun randomiQuiz() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(
                R.string.title_android_trivia_question,
                questionIndex + 1, numberQuestion
            )
    }

}
