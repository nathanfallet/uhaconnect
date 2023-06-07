package me.nathanfallet.uhaconnect.features.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.nathanfallet.uhaconnect.R
import me.nathanfallet.uhaconnect.ui.theme.component.Post
import me.nathanfallet.uhaconnect.ui.theme.component.PostCard
import me.nathanfallet.uhaconnect.ui.theme.darkBlue


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileView(modifier: Modifier){
    val username = "Username"
    val role = "role"
    val post1 = Post("07/06/2023", "Le post", "Trop cool les posts, on adore ça")
    val posts = listOf(post1, post1, post1)
    LazyColumn(modifier){
        stickyHeader {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        TextButton(onClick = { /*Logout the user*/ }) {
                            Text(text = stringResource(R.string.profile_logout), color = Color.White, fontSize = 16.sp)
                        }
                        Text(text = stringResource(R.string.profile_profile), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        TextButton(onClick = { /*Logout the user*/ }, modifier = Modifier.padding(end = 8.dp)) {
                            Text(text = stringResource(R.string.profile_settings), color = Color.White, fontSize = 16.sp)
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = darkBlue,
                    titleContentColor = Color.White
                )
            )
        }
        item{
            Box(modifier = Modifier
                .fillMaxSize()
                .height(160.dp)
                .background(Color(0xFFE8E8E8))
            ){
                Box(modifier = Modifier
                    .background(darkBlue)
                    .height(80.dp)
                    .fillMaxWidth()
                )
                Row(modifier = Modifier.padding(vertical = 30.dp, horizontal = 16.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(
                                BorderStroke(3.dp, Color.White),
                                CircleShape
                            )
                    )
                    Column(modifier = Modifier.padding(vertical = 12.dp)) {
                        Text(
                            text = "$username",
                            fontSize = 30.sp,
                            color = Color.Yellow,
                            modifier = Modifier.padding(start = 15.dp)
                        )
                        Text(
                            text = "$role",
                            modifier = Modifier.padding(start = 15.dp)
                        )
                    }
                }
            }
        }
        items(posts) { post ->
            PostCard(post)
        }
    }
}

class Post(pub_date:String, title:String, content:String)

@Composable
@Preview
fun PreviewPostView(){
    ProfileView(modifier = Modifier)
}




