package com.rraya.flopp

import com.rraya.flopp.data.VideoGameRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class VideoGameViewModelTest {

    @Mock
    lateinit var mockRepository: VideoGameRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `Test video game live data gets populated from Repo`() {
    }

    @Test
    fun `Details should be filled when selectedGame is changed`() {
    }
}
