
package com.example.sweetbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = SharedPrefManager.getInstance(this).user
        txtbienvenido.text = txtbienvenido.text.toString() + user.nombre.toString()
      btnCrearPastel.setOnClickListener {
          startActivity(Intent(applicationContext,Crear::class.java))
      }
        btnCarrito.setOnClickListener{
            startActivity(Intent(applicationContext,Carrito::class.java))
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_settings) {
    cerrarSesion()
        }
        else {
            return super.onOptionsItemSelected(item)
        }
    return true
    }
    fun cerrarSesion()
    {
        SharedPrefManager.getInstance(this).logout()
        finish()
    }
}
