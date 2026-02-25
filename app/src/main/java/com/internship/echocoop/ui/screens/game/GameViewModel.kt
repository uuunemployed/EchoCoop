package com.internship.echocoop.ui.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.internship.echocoop.data.GameRecord
import com.internship.echocoop.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(private val repository: Repository) : ViewModel() {
    var score by mutableStateOf(0)
    var lives by mutableStateOf(3)
    var rotationAngle by mutableStateOf(0f)
    var isGameOver by mutableStateOf(false)
    var isPaused by mutableStateOf(false)
    private var isResultSaved = false

    val isPlaying: Boolean get() = !isGameOver && !isPaused

    val currentTopCornerIndex: Int
        get() {
            val normalizedAngle = ((rotationAngle.toInt() % 360 + 360) % 360)
            return normalizedAngle / 120
        }

    fun togglePause() {
        if (!isGameOver) isPaused = !isPaused
    }

    fun rotate() {
        if (isPlaying) rotationAngle += 120f
    }

    fun handleCollision(stripColorIndex: Int) {
        if (stripColorIndex == currentTopCornerIndex) {
            score++
        } else {
            lives--
            if (lives <= 0 && !isGameOver) {
                isGameOver = true
                saveFinalScore()
            }
        }
    }

    private fun saveFinalScore() {
        if (score > 0 && !isResultSaved) {
            isResultSaved = true
            viewModelScope.launch(Dispatchers.IO) {
                repository.saveRecord(
                    GameRecord(score = score, date = System.currentTimeMillis())
                )
            }
        }
    }

    fun resetGame() {
        score = 0
        lives = 3
        isGameOver = false
        isPaused = false
        rotationAngle = 0f
        isResultSaved = false
    }
}

class GameViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}