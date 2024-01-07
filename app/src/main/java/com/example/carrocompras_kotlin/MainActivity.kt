package com.example.carrocompras_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrocompras_kotlin.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdaptadorProducto

    var listaProductos = ArrayList<Producto>()
    var carroCompras = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        agregarProductos()

        setupRecyclerView()

        leerSharedPreferences()
    }

    fun setupRecyclerView() {
        binding.rvListaProductos.layoutManager = LinearLayoutManager(this)
        adapter = AdaptadorProducto(this, binding.tvCantProductos, binding.btnVerCarro, listaProductos, carroCompras)
        binding.rvListaProductos.adapter = adapter
    }

    fun agregarProductos() {
        listaProductos.add(Producto("1", "Arroz", "1 kilo", 500.0))
        listaProductos.add(Producto("2", "Salsa de Tomate", "1 Sobre", 800.0))
        listaProductos.add(Producto("3", "Sal", "1 kilo", 400.0))
        listaProductos.add(Producto("4", "Queso", "1 kilo", 2000.0))

    }

    fun leerSharedPreferences() {
        val sp = this.getSharedPreferences("carro_compras", MODE_PRIVATE)

        val jsonString = sp.getString("productos", "")

        val jsonArray = JSONArray(jsonString)

        if (jsonArray != null) {
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                carroCompras.add(
                    Producto(
                    jsonObject.getString("idProducto"),
                    jsonObject.getString("nomProducto"),
                    jsonObject.getString("descripcion"),
                    jsonObject.getDouble("precio")
                )
                )
            }
        }
    }
}
