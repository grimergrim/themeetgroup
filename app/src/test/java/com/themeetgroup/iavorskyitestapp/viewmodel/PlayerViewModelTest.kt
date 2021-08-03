package com.themeetgroup.iavorskyitestapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.themeetgroup.iavorskyitestapp.data.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlayerViewModelTest {

    private lateinit var playerViewModel: PlayerViewModel
    private val name = "John"

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository

    @Before
    fun setUp() {
        playerViewModel = PlayerViewModel(mockRepository)
    }

    @Test
    fun getPlayersRatings() { //TODO find a reason why this test fail if I run all tests together and pass when I run it alone.
        playerViewModel.getPlayersRatings()
        verify(mockRepository, times(1)).getAllPlayers()
    }

    @Test
    fun getPlayersNames() {
        playerViewModel.getPlayersNames()
        verify(mockRepository, times(1)).getAllPlayersNames()
    }

    @Test
    fun savePlayer() {
        playerViewModel.savePlayer(name)
        verify(mockRepository, times(1)).findPlayerByName(name)
    }

}