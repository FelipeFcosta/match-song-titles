package com.example.processoseletivoletras
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.processoseletivoletras.adapter.SongsAdapter
import com.example.processoseletivoletras.data.Datasource
import com.example.processoseletivoletras.model.SongItem

/*  DESCRIÇÃO GERAL DO PROGRAMA:
        Foi utilizado o widget RecycleView para exibir os dados na tela sempre que o usuário
    modificar a sua entrada na barra de pesquisa.

        Os dados foram convertidos em um string-array no arquivo string.xml, e para utilizar no
    programa, transformamos as strings em uma lista do tipo <SongItem> através da classe DataSource.
    SongItem é uma classe de dados que guarda o título da música e o seu score.

        Utilizamos a função de lowercase() do Kotlin e a função criada String.removeAccents()
    para tratar o texto de entrada e os títulos das músicas *antes* da aplicação do algoritmo.
    Agora podemos comparar o texto de entrada com os nomes do banco de músicas e calcular o score
    correspondente para cada SongItem.

    Algoritmo:
        Para obtermos o score chamamos a função matchSongTitle, da classe SimilarityScore, dentro da
    função GetFilter(), implementada pela classe SongsAdapter (adapter personalizado do nosso
    recyclerview).

    matchSongTitle(title, query): Nessa função, separamos as palavras e as guardamos em listas,
    iteramos de forma a comparar todas as palavras, se as palavras são iguais adiciona-se 10 +
    palavra.length ao score, caso contrário, iteramos por cada letra das duas palavras utilizando
    um mesmo índice, adiciona-se então 1 ponto para cada letra igual. Na primeira iterada pelo
    título da música, descontamos 5 pontos se o nome conter a palavra 'feat'.

        Após obtermos todos os scores, ordenamos os SongItems em ordem decrescente de score utilizando
    o sortByDescending() do Kotlin e pegamos os 10 primeiros itens para exibir ao usuário em forma
    de uma scrollable list.
 */

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


        // detecta quando o usuário digita/apaga algo na barra de pesquisa
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
