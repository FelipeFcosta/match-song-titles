package com.example.processoseletivoletras.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.processoseletivoletras.NUM_DISPLAY_SONGS
import com.example.processoseletivoletras.SimilarityScore
import com.example.processoseletivoletras.model.SongItem
import com.example.processoseletivoletras.removeAccents
import kotlin.collections.ArrayList


// SongsAdapter transforma a lista de objetos (songItems) em views list_item com
// os dados das músicas
class SongsAdapter(
    private val songItems: List<SongItem>
) : RecyclerView.Adapter<SongsAdapter.ItemViewHolder>(), Filterable {

    // guarda todos os nomes das músicas já tratadas
    private var normalizedTitles = songItems.map { it.title.removeAccents().lowercase() }

    // lista das músicas que vão aparecer na tela
    private var songsToDisplay = ArrayList<SongItem>()

    // ViewHolder representa um list_item view
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textScore: TextView = view.findViewById(com.example.processoseletivoletras.R.id.score)
        val textTitle: TextView = view.findViewById(com.example.processoseletivoletras.R.id.title)
    }

    // Cria novas views list_item (chamado pelo layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(com.example.processoseletivoletras.R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    // Substitui o conteúdo de uma view (chamado pelo layout manager)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = songsToDisplay[position]
        holder.textScore.text = item.score.toString()
        holder.textTitle.text = item.title
    }

    // Retorna o tamanho do dataset (chamado pelo layout manager)
    override fun getItemCount() = songsToDisplay.size


    // Filtra as músicas a serem mostradas
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                // tratamento da pesquisa do usuário
                val input = constraint.toString().removeAccents().lowercase()

                // realizar operação de filtro
                val filterResults = FilterResults()
                filterResults.values = getFilteredSongItems(input, NUM_DISPLAY_SONGS)
                return filterResults
            }

            @Suppress("UNCHECKED_CAST", "NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                // adiciona as músicas filtradas na lista de exibição e notifica o recycler view
                songsToDisplay.clear()
                songsToDisplay.addAll(results?.values as ArrayList<SongItem>)
                notifyDataSetChanged()
            }

            /**
             * @param input Entrada do usuário na barra de pesquisa
             * @param n Quantidade de SongItems a serem retornados
             * @return Lista dos [n] SongItems com os maiores scores
             */
            fun getFilteredSongItems(input: String, n: Int): ArrayList<SongItem> {
                var filteredSongs = ArrayList<SongItem>()

                if (input.isNotEmpty()) {
                    // calcula o score de cada música da lista e adiciona à lista filtrada
                    for ((idx, songItem) in songItems.withIndex()) {
                        songItem.score = SimilarityScore.matchSongTitle(normalizedTitles[idx], input)
                        filteredSongs.add(songItem)
                    }
                    // ordena os músicas por score em ordem decrescente e pegar os n primeiros
                    filteredSongs.sortByDescending { songItem -> songItem.score }
                    filteredSongs = filteredSongs.take(n) as ArrayList<SongItem>
                }

                return filteredSongs
            }
        }
    }
}