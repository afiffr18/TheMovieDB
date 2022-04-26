package id.afif.binarchallenge5.Helper

import android.content.Context
import id.afif.binarchallenge5.database.User
import kotlinx.coroutines.withContext

class SharePref(context : Context) {

    private val sharePref = context.getSharedPreferences("share_key",Context.MODE_PRIVATE)
    val editor = sharePref.edit()

    fun setData(username : String,password : String){
        editor.putString(KEY_USERNAME,username)
        editor.putString(KEY_PASSWORD,password)

        editor.putBoolean(KEY_LOGIN,true)

        editor.apply()
    }

    fun isLogedIn():Boolean{
        return sharePref.getBoolean(KEY_LOGIN,false)
    }

    fun getUsername() : String{
        return sharePref.getString(KEY_USERNAME,"default").toString()
    }

    fun getPassword() : String{
        return sharePref.getString(KEY_PASSWORD,"default").toString()
    }

    fun lougoutSession(){
        editor.clear()
        editor.apply()
    }


    companion object{
        const val KEY_LOGIN = "adsfs"
        const val KEY_ID = "adsgjhejkeak"
        const val KEY_USERNAME = "keadf"
        const val KEY_PASSWORD = "adsfa"
        const val KEY_FULLNAME = "agrjhwkt"
        const val KEY_TANGGAL = "Tsadjkas"
        const val KEY_ALAMAT = "asdgrthk23i1"
    }


}