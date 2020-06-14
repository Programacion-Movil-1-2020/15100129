package com.example.sweetbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_crear.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import org.json.JSONException
import org.json.JSONObject
import java.time.Month
import java.time.Year

class Crear : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val user = SharedPrefManager.getInstance(this).user
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear)
        ArrayAdapter.createFromResource(
                this,
        R.array.sabores,
        android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSabores.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.betun,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerbetun.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.tamano,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnertamano.adapter = adapter
        }

        btn_crear.setOnClickListener {
            alert("Desea finalizar el pedido?"){
                yesButton { realizarPedido(user.id) }
                noButton {  }
            }.show()


        }

    }
    fun realizarPedido(id: Int)
    {
        val pan = spinnerSabores.selectedItem.toString()
        val betun = spinnerbetun.selectedItem.toString()
        val tamano = spinnertamano.selectedItem.toString()
        var forma = ""
        if (rbcircular.isChecked)
        {
            forma = "circular"
        }
        else
        {
            forma = "cuadrado"
        }
        val fecha_entrega = calendarView.date.toString()
        val url = "https://obamium.xyz/SweetAPI/operaciones.php?op=realizarPedido"
        val req = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener { response ->
                try{
                    //Convierte el response en JSONObject
                    val obj = JSONObject(response)

                    //Si no hubo errores en el response
                    if(!obj.getBoolean("error"))
                    {
                        Toast.makeText(applicationContext,obj.getString("message"), Toast.LENGTH_SHORT).show()
                        //empezar el activity login
                        finish()
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                    }else{
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_SHORT).show()
                    }

                }catch(e: JSONException){
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(applicationContext,error.toString(), Toast.LENGTH_SHORT).show()


            })
        {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["id_usuario"] = id.toString()
                params["pan"] = pan
                params["betun"] = betun
                params["tamano"] = tamano
                params["forma"] = forma
                params["fecha_entrega"] = fecha_entrega
                return params
            }
        }
        VolleySingleton.getInstance(this).addToRequestQueue(req)
    }
}
