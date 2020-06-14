package com.example.sweetbite

import android.content.Context
import android.content.Intent
import org.jetbrains.anko.db.NULL

class SharedPrefManager private constructor(context: Context) {

    //Este metodo verifica si hay un usuario loggeado
    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_USUARIO, null) != null
        }

    //Este metodo regresa el usuario loggeado
    val user: User
        get() {
            val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                sharedPreferences!!.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USUARIO, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_NOMBRE, null),
                sharedPreferences.getString(KEY_APELLIDO,null)
            )
        }
    init {
        ctx = context
    }

    //este metodo guarda la informacion del usuario
    fun userLogin(user: User) {
        val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putInt(KEY_ID, user.id)
        editor?.putString(KEY_USUARIO, user.usuario)
        editor?.putString(KEY_EMAIL, user.email)
        editor?.putString(KEY_NOMBRE, user.nombre)
        editor?.putString(KEY_APELLIDO,user.apellido)
        editor?.apply()
    }

    //este metodo hace un logout del usuario loggeado
    fun logout() {
        val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
        ctx?.startActivity(Intent(ctx, Login::class.java))

    }

    companion object {

        private val SHARED_PREF_NAME = "volleyregisterlogin"
        private val KEY_USUARIO = "keyusuario"
        private val KEY_EMAIL = "keyemail"
        private val KEY_NOMBRE = "keyapellido"
        private val KEY_APELLIDO = "keynombre"
        private val KEY_ID = "keyid"
        private var mInstance: SharedPrefManager? = null
        private var ctx: Context? = null
        @Synchronized
        fun getInstance(context: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(context)
            }
            return mInstance as SharedPrefManager
        }
    }
}