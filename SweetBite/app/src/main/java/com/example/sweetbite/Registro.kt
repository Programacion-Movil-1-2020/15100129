package com.example.sweetbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.activity_registro.password_edit_text
import kotlinx.android.synthetic.main.activity_registro.user_edit_text
import org.json.JSONException
import org.json.JSONObject

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
     btnRegresar.setOnClickListener {
     finish()
     }
        btnReg.setOnClickListener {
            Registrarse()
        }
    }
    fun Registrarse(){
        //Url a funcion de login en API RESTFUL
        val url = "https://obamium.xyz/SweetAPI/operaciones.php?op=registro"
        val email = email_edit_text.text.toString().trim()
        val username = user_edit_text.text.toString().trim()
        val password = password_edit_text.text.toString().trim()
        val name = name_edit_text.text.toString().trim()
        val last = last_edit_text.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            email_edit_text.error = "Porfavor ingrese su correo electronico"
            email_edit_text.requestFocus()
            return
        }
        if (TextUtils.isEmpty(username)) {
            user_edit_text.error = "Porfavor ingrese su usuario"
            user_edit_text.requestFocus()
            return
        }
        if (TextUtils.isEmpty(password)) {
            password_edit_text.error = "Porfavor ingrese su contraseña"
            password_edit_text.requestFocus()
            return
        }
        if (TextUtils.isEmpty(name)) {
            name_edit_text.error = "Porfavor ingrese su nombre"
            name_edit_text.requestFocus()
            return
        }
        if (TextUtils.isEmpty(last)) {
            last_edit_text.error = "Porfavor ingrese su apellido"
            last_edit_text.requestFocus()
            return
        }
        //Si todo esta bien:
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
                        startActivity(Intent(applicationContext,Login::class.java))
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
                params["email"] = email
                params["username"] = username
                params["password"] = password
                params["nombre"] = name
                params["apellido"] = last
                return params
            }
        }
        VolleySingleton.getInstance(this).addToRequestQueue(req)
    }


}
