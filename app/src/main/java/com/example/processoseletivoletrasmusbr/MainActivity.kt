package com.example.processoseletivoletrasmusbr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.processoseletivoletrasmusbr.adapter.ItemAdapter
import com.example.processoseletivoletrasmusbr.data.Datasource
import com.example.processoseletivoletrasmusbr.model.Title

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBar: EditText = findViewById(R.id.search_song)

        // Initialize data.
        val titles: List<Title> = Datasource(this).loadTitles()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(titles)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

    }
}