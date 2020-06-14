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
import org.json.JSONException
import org.json.JSONObject

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if(SharedPrefManager.getInstance(this).isLoggedIn)
        {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
        btnRegistrarse.setOnClickListener {
            startActivity(Intent(this,Registro::class.java))
        }
        btnLogin.setOnClickListener {
            login()
           // Toast.makeText(applicationContext,"heeey", Toast.LENGTH_SHORT).show()

        }
    }
    fun login() {
        //Url a funcion de login en API RESTFUL
        val url = "https://obamium.xyz/SweetAPI/operaciones.php?op=login"

        //obtener el valor de los edit text:
        val username = user_edit_text.text.toString().trim()
        val password = password_edit_text.text.toString().trim()

        //validar que no esten vacios los campos:
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

                        //Obtener el usuario del response
                        val userJson = obj.getJSONObject("user")

                        //Crear objeto de Sesion
                        val user = User(userJson.getInt("id"),
                            userJson.getString("usuario"),
                            userJson.getString("email"),
                            userJson.getString("nombre"),
                            userJson.getString("apellido"))

                        //guardar las preferencias
                        SharedPrefManager.getInstance(applicationContext).userLogin(user)

                        //empezar el activity menu
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
                params["username"] = username
                params["password"] = password
                return params
            }
        }
        VolleySingleton.getInstance(this).addToRequestQueue(req)
    }
}
