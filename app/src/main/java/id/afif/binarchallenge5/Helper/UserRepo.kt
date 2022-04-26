package id.afif.binarchallenge5.Helper

import android.content.Context
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import id.afif.binarchallenge5.database.User
import id.afif.binarchallenge5.database.UserDao
import id.afif.binarchallenge5.database.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepo(context: Context) {
    private val mDb = UserDatabase.getInstance(context)


    suspend fun saveRegister(user : User) = withContext(Dispatchers.IO){
        mDb?.userDao()?.insertUser(user)
    }

    suspend fun getDataById(username : String,password:String) = withContext(Dispatchers.IO){
        mDb?.userDao()?.getDataById(username,password)
    }

    suspend fun updateData(user : User) = withContext(Dispatchers.IO){
        mDb?.userDao()?.updateUser(user)
    }


}