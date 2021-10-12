package com.example.processoseletivoletras

class SimilarityScore {
    companion object {

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

            var charPos = 0 // posição da letra das palavras
            for ((qWordIdx, queryWord) in queryWords.withIndex()) { // para cada palavra da busca
                for (titleWord in titleWords) {                     // para cada palavra da música

                    // Contabiliza de palavras idênticas, se são iguais, já pulamos para
                    // a próxima palavra após incrementar o score
                    if (queryWord == titleWord) {
                        score += PONTOS_PALAVRA_IGUAL + queryWord.length
                    } else {
                        // Enquanto a posição da letra estiver dentro dos limites das palavras,
                        // contabilizamos as letras que são iguais
                        while (!isOutOfBounds(titleWord, queryWord, charPos)) {
                            if (queryWord[charPos] == titleWord[charPos]) {
                                score += PONTOS_LETRA_IGUAL
                            }
                            charPos++
                        }

                        // Contabilização de 'feat' (apenas na primeira iteração,
                        // para não contabilizar excessivamente)
                        if (qWordIdx == 0 && titleWord == "feat") {
                            score += PONTOS_CONTEM_FEAT
                        }
                        charPos = 0
                    }
                }
            }

            return score
        }

        /**
         * Verifica se a posição [pos] está fora dos limites para alguma das palavras
         * @param str1 primeira palavra
         * @param str2 segunda palavra
         * @param pos posição da letra
         */
        private fun isOutOfBounds(str1: String, str2: String, pos: Int) : Boolean {
            return pos >= str1.length || pos >= str2.length
        }
    }
}