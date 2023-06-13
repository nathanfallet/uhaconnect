package me.nathanfallet.uhaconnect.features.feed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nathanfallet.uhaconnect.models.Post
import me.nathanfallet.uhaconnect.models.UpdatePostPayload
import me.nathanfallet.uhaconnect.models.User
import me.nathanfallet.uhaconnect.services.APIService

class FeedViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    val loader: String? = savedStateHandle["loader"]

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private val _user = MutableLiveData<User>()

    private val api = APIService.getInstance(Unit)

    fun loadData(token: String?) {
        if (token == null) {
            return
        }
        viewModelScope.launch {
            try {
                _posts.value = when (loader) {
                    "posts" -> api.getPosts(token)
                    "favorites" -> api.getFavorites(token)
                    "validation" -> api.getPostsRequests(token)
                    else -> listOf()
                }
            } catch (e: Exception) {

            }
        }
    }

    fun deletePost(token: String?, id: Int) {
        if (token == null) return
        viewModelScope.launch {
            try {
                APIService.getInstance(Unit).deletePost(token, id)
                _posts.value = _posts.value?.filter { it.id != id }
            } catch (e: Exception) {
                //TODO: ERRORS
            }
        }
    }

    fun updatePost(token: String?, id: Int, payload: UpdatePostPayload) {
        if (token == null) return
        viewModelScope.launch {
            try {
                APIService.getInstance(Unit).updatePost(token, id, payload)
                if (payload == UpdatePostPayload(validated = true)) {
                    _posts.value = _posts.value?.filter { it.id != id }
                }
            } catch (e: Exception) {
                //TODO: ERRORS
            }
        }
    }

    fun favoritesHandle(token: String?, postId: Int, addOrDelete: Boolean) {
        if (token == null) return
        viewModelScope.launch {
            try {
                if (!addOrDelete) api.addToFavorites(token, postId)
                else api.deleteToFavorites(token, postId)
                loadData(token)
            } catch (e: Exception) {
            }
        }
    }

}



