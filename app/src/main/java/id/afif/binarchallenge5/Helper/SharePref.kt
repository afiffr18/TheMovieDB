package id.afif.binarchallenge5.Helper

import android.content.Context
import id.afif.binarchallenge5.database.User
import kotlinx.coroutines.withContext

class SharePref(context : Context) {

    private val sharePref = context.getSharedPreferences("share_key",Context.MODE_PRIVATE)
    val editor = sharePref.edit()

    fun setData(user : User){
        editor.putString(KEY_USERNAME,user.username)
        editor.putString(KEY_EMAIL,user.email)
        editor.putString(KEY_FULLNAME,user.fullname)
        editor.putString(KEY_TANGGAL,user.tanggal)
        editor.putString(KEY_ALAMAT,user.alamat)
        editor.putBoolean(KEY_LOGIN,true)

        editor.apply()

    }

    fun isLogedIn():Boolean{
        return sharePref.getBoolean(KEY_LOGIN,false)
    }

    fun getUsername(){
        sharePref.getString(KEY_USERNAME,"default")
    }


    companion object{
        const val KEY_LOGIN = "adsfs"
        const val KEY_USERNAME = "keadf"
        const val KEY_EMAIL = "adsfa"
        const val KEY_FULLNAME = "agrjhwkt"
        const val KEY_TANGGAL = "Tsadjkas"
        const val KEY_ALAMAT = "asdgrthk23i1"
    }


}