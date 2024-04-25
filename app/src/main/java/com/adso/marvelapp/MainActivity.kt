package com.adso.marvelapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adso.marvelapp.adapter.HorizontalRecyclerViewAdapter
import com.adso.marvelapp.adapter.RecyclerViewAdapter
import com.adso.marvelapp.data.DataSource
import com.adso.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.onItemClicListener,
    HorizontalRecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var miRecycler: RecyclerView
    private lateinit var horizontalRecyclerView: RecyclerView
    private lateinit var miAdapter: RecyclerViewAdapter
    private lateinit var horizontalAdapter: HorizontalRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el RecyclerView vertical
        miRecycler = binding.rvsuperheroes
        miRecycler.setHasFixedSize(true)
        miRecycler.layoutManager = LinearLayoutManager(this)
        miAdapter = RecyclerViewAdapter(DataSource().getSuperHeroes(), this)
        miRecycler.adapter = miAdapter
        miAdapter.setOnItemClickListener(this)

        // Configurar el RecyclerView horizontal
        horizontalRecyclerView = binding.horizontalRecyclerView
        horizontalRecyclerView.setHasFixedSize(true)
        horizontalRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        horizontalAdapter = HorizontalRecyclerViewAdapter(DataSource().getSuperHeroes())
        horizontalRecyclerView.adapter = horizontalAdapter
        horizontalAdapter.setOnItemClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.game_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Ajustes -> {
                Log.d("OptionsMenu", "Settings picked")
                true
            }
            R.id.habilidad -> {
                Log.d("OptionsMenu", "Logout picked")
                true
            }
            R.id.carpeta -> {
                Log.d("OptionsMenu", "Logout picked")
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    // Método de la interfaz para el RecyclerView vertical
    override fun onItemClickListener(position: Int) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("nombre", DataSource().getSuperHeroes()[position].nombre)
        intent.putExtra("nombreReal", DataSource().getSuperHeroes()[position].nombreReal)
        intent.putExtra("publisher", DataSource().getSuperHeroes()[position].publisher)
        intent.putExtra("imagen", DataSource().getSuperHeroes()[position].foto)
        startActivity(intent)
    }

    // Método de la interfaz para el RecyclerView horizontal
    override fun onItemClick(position: Int) {
        val clickedItem = DataSource().getSuperHeroes()[position]
        Toast.makeText(
            this@MainActivity,
            "SE SELECCIONO EL ITEM: ${clickedItem.nombre}",
            Toast.LENGTH_SHORT
        ).show()
    }
}
