package kr.hs.dgsw.juyeop.interview.viewmodel.activity

import android.content.Context
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.domain.entity.Solution
import kr.hs.dgsw.juyeop.domain.usecase.question.GetAllQuestionUseCase
import kr.hs.dgsw.juyeop.domain.usecase.solution.GetAllSolutionUseCase
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.view.adapter.QuestionItemAdapter
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager

class MySolutionViewModel(
    private val context: Context,
    private val getAllSolutionUseCase: GetAllSolutionUseCase,
    private val getAllQuestionUseCase: GetAllQuestionUseCase
) : BaseViewModel() {

    var allQuestionList = ArrayList<Question>()
    var allSolutionList = ArrayList<Solution>()

    var questionItemList = ArrayList<Question>()
    var questionItemAdapter = QuestionItemAdapter()

    var category = MutableLiveData<Int>()
    val onCombinEvent = SingleLiveEvent<Unit>()

    init {
        getAllQuestion()
        questionItemAdapter.setList(questionItemList, true)
    }

    fun getAllQuestion() {
        addDisposable(getAllQuestionUseCase.buildUseCaseObservable(), object : DisposableSingleObserver<List<Question>>() {
            override fun onSuccess(questionList: List<Question>) {
                allQuestionList.addAll(questionList)
                category.value = 1
            }
            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        })
    }

    fun getAllSolution() {
        val userId = SharedPreferencesManager.getUserId(context).toString()

        addDisposable(getAllSolutionUseCase.buildUseCaseObservable(GetAllSolutionUseCase.Params(userId)), object : DisposableSingleObserver<List<Solution>>() {
            override fun onSuccess(solutionList: List<Solution>) {
                allSolutionList.clear()
                allSolutionList.addAll(solutionList)
                combinData()
            }
            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }
        })
    }

    fun combinData() {
        val categoryQuestionList = allQuestionList.filter { question ->
            question.category == category.value
        } as ArrayList<Question>

        val userQuestionList = ArrayList<Question>()
        allSolutionList.forEach { solution ->
            val elementList = categoryQuestionList.filter { question ->
                solution.question_idx == question.idx
            }
            if (elementList.isNotEmpty()) userQuestionList.add(elementList[0])
        }
        questionItemList.clear()
        questionItemList.addAll(userQuestionList)
        questionItemAdapter.notifyDataSetChanged()

        onCombinEvent.call()
    }

    fun getColorResource(category: Int): Int {
        var colorResource = R.color.colorCategory1
        when(category) {
            1 -> colorResource = R.color.colorCategory1
            2 -> colorResource = R.color.colorCategory2
            3 -> colorResource = R.color.colorCategory3
            4 -> colorResource = R.color.colorCategory4
            5 -> colorResource = R.color.colorCategory5
            6 -> colorResource = R.color.colorCategory6
            7 -> colorResource = R.color.colorCategory7
        }
        return colorResource
    }
}