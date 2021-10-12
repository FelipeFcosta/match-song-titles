package com.example.processoseletivoletras.data

import android.content.Context
import com.example.processoseletivoletras.R
import com.example.processoseletivoletras.model.SongItem
import com.example.processoseletivoletras.removeAccents

class Datasource(private val context: Context) {
    /**
     * Carrega todas as músicas a partir do string-array no strings.xml
     * @return lista de todas as músicas
    */
    fun loadSongs(): List<SongItem> {
        return context.resources.getStringArray(R.array.titles)
            .map { SongItem(it) }
    }
}