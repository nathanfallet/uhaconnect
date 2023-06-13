package me.nathanfallet.uhaconnect.features.parameters

import android.content.res.Resources
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.nathanfallet.uhaconnect.R
import me.nathanfallet.uhaconnect.features.login.LoginViewModel
import me.nathanfallet.uhaconnect.models.User
import me.nathanfallet.uhaconnect.ui.theme.darkBlue
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ParametersView(modifier: Modifier, token: String?, user: User?){

    val viewModel: ParametersViewModel = viewModel()

    val newUsername by viewModel.newUsername.observeAsState("")
    val oldPw by viewModel.oldPassword.observeAsState("")
    val newPw by viewModel.newPassword.observeAsState("")
    val rpPw by viewModel.newPassword2.observeAsState("")


    LazyColumn(modifier){
        stickyHeader{
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = darkBlue,
                    titleContentColor = Color.White
                )
            )
        }
        item{
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(160.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(darkBlue)
                        .height(80.dp)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box{
                        Image(
                            painter = painterResource(R.drawable.ic_launcher_background),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { /*change profile picture*/ }
                                .size(100.dp)
                                .clip(CircleShape)
                                .border(
                                    BorderStroke(3.dp, Color.White),
                                    CircleShape
                                )
                        )
                        Icon(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = null,
                            modifier = Modifier
                                .offset {
                                    IntOffset(x = 0, y = +200)
                                }
                                .clip(CircleShape)
                                .size(30.dp)
                                .background(Color.White),
                            tint = Color.Black
                        )
                    }
                }
            }
            Column (modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp)) {
                Text(text = stringResource(R.string.parameters_change_un))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()){
                    OutlinedTextField(
                        value = newUsername,
                        onValueChange = { viewModel.newUsername.value = it },
                        label = { stringResource(R.string.login_username) },
                        modifier = Modifier
                            .width(200.dp)
                            .padding(vertical = 8.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        keyboardActions = KeyboardActions(),
                        textStyle = LocalTextStyle.current.copy(
                            fontFamily = FontFamily.Default,
                            fontSize = 14.sp
                        ),
                        singleLine = true,
                        maxLines = 1
                    )
                    Button(
                        onClick = { viewModel.changeUsername(token, user?.id)},
                        modifier = Modifier.padding(10.dp)){
                        Text(stringResource(R.string.parameters_validate))
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = "Validate",
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Divider(modifier = Modifier.padding(bottom = 8.dp, top = 8.dp))
                Text(text = stringResource(R.string.parameters_change_password))
                OutlinedTextField(
                    value = oldPw,
                    onValueChange = { viewModel.oldPassword.value = it },
                    label = { stringResource(R.string.parameters_old_password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .padding(top = 0.dp, bottom = 0.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                OutlinedTextField(
                    value = newPw,
                    onValueChange = { viewModel.newPassword.value = it },
                    label = { stringResource(R.string.parameters_new_password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .padding(top = 0.dp, bottom = 0.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                OutlinedTextField(
                    value = rpPw,
                    onValueChange = { viewModel.newPassword2.value = it },
                    label = { stringResource(R.string.parameters_repeat_password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .padding(top = 0.dp, bottom = 0.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Button(
                    onClick = {viewModel.changePassword(token, user?.id)},
                ){
                    Text(stringResource(R.string.parameters_validate))
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Validate",
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
        }
    }
}