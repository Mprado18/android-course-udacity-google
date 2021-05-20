package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object {

        private const val DONE = 0L //tempo para quando jogo for concluido
        private const val ONE_SECOND = 1000L //valor para 1s
        private const val COUNTDOWN_TIME = 10000L //duração do game 60s

    }

    private val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    // The String version of the current time
    val currentString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    // The current word
    // LiveData para salvar estado atual caso app gire, seja minimizado
    private val _word = MutableLiveData<String>() //encapsula o LiveData privado e imutável (aplica no GameFinish e score)
    val word: LiveData<String>
        get() = _word //LiveData fica apenas legível para o fragment (aplica no GameFinish e score)

    // The current score
    // LiveData para salvar estado atual caso app gire, seja minimizado
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    //LiveData para salvar estado atual do game finalizado
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        resetList() //reseta a listagem de palavras
        nextWord() //chama a próxima palavra a ser exibida
        _score.value = 0 //reseta pontuação

        //cronometro para contagem regressa e finalização do game
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }
        }

        timer.start()
    }

    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle() //randomizar lista
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
        }
        _word.value = wordList.removeAt(0)
    }

    /** Methods for buttons presses **/
    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextWord()
    }

    //irá executar a navegação para tela final apenas uma vez
    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    //limpa e cancela o timer
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}