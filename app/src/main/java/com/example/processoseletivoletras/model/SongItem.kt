package com.example.processoseletivoletras.model

/**
 * Representa uma musica.
 * @param title O nome da musica.
 * @param score Pontuacao calculada a partir do casamento entre a pesquisa feita e o nome da musica.
 */
class SongItem(val title: String, var score: Int = 0)