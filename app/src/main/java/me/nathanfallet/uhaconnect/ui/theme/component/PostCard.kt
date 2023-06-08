package me.nathanfallet.uhaconnect.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Instant
import me.nathanfallet.uhaconnect.R
import me.nathanfallet.uhaconnect.extensions.timeAgo
import me.nathanfallet.uhaconnect.models.Post


@Composable
fun PostCard(post: Post, navigate: (String)->Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .background(Color(0xFFE8E8E8))
        .border(width = 1.dp, color = Color.Black)
        .size(200.dp)
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ){
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()){
                Text(text = post.date.timeAgo(), fontSize = 12.sp, color = Color.DarkGray)
                IconButton(onClick = { navigate("post") }) {
                    Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)
                }
            }
            Text(text = post.title,
                modifier = Modifier.padding(bottom = 5.dp, top = 5.dp),
                fontSize = 24.sp,
            )
            Text(text = post.content)
        }
    }
}

val time:Instant = Instant.parse("2023-06-06T23:53:00+00:00")
val post = Post(1, 1, "Le post", "Trop cool les posts", time)

@Preview
@Composable
fun PreviewPostCard(){
    PostCard(post, {})
}