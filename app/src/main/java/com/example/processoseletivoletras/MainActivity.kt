package com.example.processoseletivoletras
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.processoseletivoletras.adapter.SongsAdapter
import com.example.processoseletivoletras.data.Datasource
import com.example.processoseletivoletras.model.SongItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBar: SearchView = findViewById(R.id.search_song)

        // Inicializar dados com score = 0
        val songItems: List<SongItem> = Datasource(this).loadSongs()

        val songsAdapter = SongsAdapter(songItems)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = songsAdapter
        recyclerView.setHasFixedSize(true)

        // detecta quando o usuário digita algo na barra de pesquisa
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                // entrega o input para o filtro a ser aplicado no songsAdapter
                songsAdapter.filter.filter(query ?: "")
                return false
            }

        })

    }
}
