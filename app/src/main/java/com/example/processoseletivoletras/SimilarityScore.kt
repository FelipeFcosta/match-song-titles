package com.example.processoseletivoletras

class SimilarityScore {
    companion object {
        private const val WORD_EQUALS_POINTS = 10
        private const val LETTER_EQUALS_POINTS = 1
        private const val CONTAINS_FEAT_POINTS = -5

        /**
         * Compara cada palavra da busca (query) com cada palavra do nome da música e calcula
         * uma pontuação que reflete a similaridade entre as palavras
         * @param songTitle nome da música
         * @param query busca do usuário
         * @return pontuação calculada a partir do casamento das palavras do input e
         * do nome da música
         */
        fun matchSongTitle(songTitle: String, query: String): Int {
            var score = 0

            // guarda as palavras em um Array (separadas por um ou mais espaços em branco)
            val titleWords = songTitle.split("\\s+".toRegex())
            val queryWords = query.split("\\s+".toRegex())

            for ((qWordIdx, queryWord) in queryWords.withIndex()) {
                for (titleWord in titleWords) {
                    // Contabilização de palavras idênticas
                    if (queryWord == titleWord) {
                        score += WORD_EQUALS_POINTS + queryWord.length
                    } else {
                        // Contabilização de letras
                        score += matchLetters(queryWord, titleWord)

                        // Contabilização de 'feat' (apenas na primeira iteração)
                        if (qWordIdx == 0 && titleWord == "feat") {
                            score += CONTAINS_FEAT_POINTS
                        }
                    }
                }
            }

            return score
        }

        /**
         * Verifica se a posição [pos] está fora dos limites para alguma das 2 palavras
         */
        private fun isPosOutOfBounds(str1: String, str2: String, pos: Int) : Boolean {
            return pos >= str1.length || pos >= str2.length
        }

        /**
         * Contabiliza as letras que são iguais
         * @return score obtido através do casamento entre as letras das palavras
         */
        private fun matchLetters(str1: String, str2: String): Int {
            var score = 0
            var charPos = 0 // posição da letra das palavras
            // Enquanto a posição da letra estiver dentro dos limites das palavras
            while (!isPosOutOfBounds(str1, str2, charPos)) {
                if (str1[charPos] == str2[charPos]) {
                    score += LETTER_EQUALS_POINTS
                }
                charPos++
            }
            return score
        }
    }
}