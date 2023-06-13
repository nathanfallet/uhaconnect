package me.nathanfallet.uhaconnect.features.parameters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nathanfallet.uhaconnect.R
import me.nathanfallet.uhaconnect.models.UpdateUserPayload
import me.nathanfallet.uhaconnect.models.User
import me.nathanfallet.uhaconnect.services.APIService

class ParametersViewModel: ViewModel() {

    val error = MutableLiveData<Int>()
    val newUsername = MutableLiveData("")
    val newPassword = MutableLiveData("")
    val newPassword2 = MutableLiveData("")

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun changeUsername(token: String?, id: Int?){
        if (token == null || id == null) return
        if (isUsernameValid()) {
            viewModelScope.launch {
                try {
                    APIService.getInstance(Unit).updateUser(
                        token,
                        id,
                        UpdateUserPayload(
                            null,
                            null,
                            username = newUsername.value ?: "",
                            null,
                            null,
                            null
                        )
                    )
                } catch (e: Exception) {
                    Log.d("ParametersViewModel", e.toString())
                    error.value = R.string.parameters_invalid_un_credentials
                }
            }
        } else null ?: run {
            Log.d("ParametersViewModel", "UsernameNotValid")
            error.value = R.string.parameters_invalid_un_credentials
        }
    }

    fun changePassword(token: String?, id: Int?){
        if (token == null || id == null) return
        if (isPasswordValid()) {
            viewModelScope.launch {
                try {
                    APIService.getInstance(Unit).updateUser(
                        token,
                        id,
                        UpdateUserPayload(
                            null,
                            null,
                            null,
                            null,
                            null,
                            newPassword.value ?: ""
                        )
                    )
                } catch (e: Exception) {
                    Log.d("ParametersViewModel", e.toString())
                    error.value = R.string.parameters_invalid_pw_credentials
                }
            }
        } else null ?: run {
            Log.d("ParametersViewModel", "UsernameNotValid")
            error.value = R.string.parameters_invalid_pw_credentials
        }
    }

    private fun isUsernameValid(): Boolean {
        return User.isUsernameValid(newUsername.value ?: "")
    }

    private fun isPasswordValid(): Boolean {
        return ((newPassword.value?.length ?: 0) >= 6) && (newPassword.value == newPassword2.value)
    }

}