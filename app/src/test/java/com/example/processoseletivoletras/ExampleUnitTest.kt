package com.example.processoseletivoletras

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun removeAccents_isCorrect() {
        assertEquals("Orgao", "Órgão".removeAccents())
        assertEquals("AEIiooac", "ÁÉÍíõôãç".removeAccents())

        val original = "ÁÍÓÚÉÄÏÖÜËÀÌÒÙÈÃÕÂÎÔÛÊáíóúéäïöüëàìòùèãõâîôûêÇç"
        val normalized = "AIOUEAIOUEAIOUEAOAIOUEaioueaioueaioueaoaioueCc"
        assertEquals(normalized, original.removeAccents())
    }

    @Test
    fun matchingAlgorithm_isCorrect() {
        assertEquals(5, SimilarityScore.matchSongTitle("fica tranquilo", "fala dica"))
        assertEquals(33, SimilarityScore.matchSongTitle("fica tranquilo", "fica tranquilo"))
        assertEquals(14, SimilarityScore.matchSongTitle("eu era", "era"))
        assertEquals(11, SimilarityScore.matchSongTitle("havana feat young thug", "havana"))
        assertEquals(14, SimilarityScore.matchSongTitle("feat", "feat"))
        assertEquals(-1, SimilarityScore.matchSongTitle("feat", "feats"))
    }
}