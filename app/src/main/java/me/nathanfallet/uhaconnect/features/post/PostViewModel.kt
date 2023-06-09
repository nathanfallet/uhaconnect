package me.nathanfallet.uhaconnect.features.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.nathanfallet.uhaconnect.models.Comment
import me.nathanfallet.uhaconnect.models.Post
import me.nathanfallet.uhaconnect.models.User
import me.nathanfallet.uhaconnect.services.APIService

class PostViewModel(token : String) : ViewModel() {

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post>
        get() = _post

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user


    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments


    init {

        viewModelScope.launch {
            try {
                val instant: kotlinx.datetime.Instant = kotlinx.datetime.Instant.parse("2023-06-07T12:34:56Z")
                val apiService = APIService.getInstance(Unit)
                val user = apiService.getUser(token, 78)
                val post = apiService.getPost(token, 55)
                val comments = listOf(
                    Comment(55, 11, "content 1",instant),
                    Comment(55, 112, "content 2",instant),
                    Comment(55, 74, "content 2",instant)
                )

                _post.value = post
                _comments.value = comments
                _user.value = user
            } catch (e: Exception) {
                null
            }
        }

    }
}