package com.example.processoseletivoletrasmusbr.data

import android.content.Context
import com.example.processoseletivoletrasmusbr.R
import com.example.processoseletivoletrasmusbr.model.Title

class Datasource(private val context: Context) {
    fun loadTitles(): List<Title> {
        return context.resources.getStringArray(R.array.titles)
            .map { title -> Title(title) }
    }
}