package com.example.composeproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeproject.ui.theme.ComposeProjectTheme

data class User(
    val name:String,
    val email:String
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()){
    val state = viewModel.state.collectAsStateWithLifecycle()
    //val action = viewModel.action.collectAsStateWithLifecycle()

    MainContent(
        viewState = state.value,
        eventHandler = viewModel::event
    )
}

@Composable
fun MainContent(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit
) {
    ComposeProjectTheme{
        LazyColumnSample(viewState,eventHandler)
    }
}

@Composable
private fun ClearIcon(
    isPassword: Boolean,
    onClick: () -> Unit
){
    IconButton(onClick = onClick) {
        Icon(painterResource(
            id = R.drawable.baseline_clear_24),
            contentDescription = null,
            tint = if (isPassword) Color.Black else Color.Red)
    }
}
@Composable
fun LazyColumnSample(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        item {
            Text(text = "Hello ${viewState.title}!")
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = viewState.email,
                visualTransformation = if (viewState.isPassword) {PasswordVisualTransformation()} else{
                    VisualTransformation.None},
                onValueChange = {
                    eventHandler.invoke(MainEvent.OnEmailChange(it))
                },
                leadingIcon = {
                    Box(modifier = Modifier.size(16.dp)) {
                        Image(
                            painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                    }
                },
                trailingIcon = {
                    ClearIcon(
                        isPassword = viewState.isPassword,
                        onClick = {
                        eventHandler.invoke(MainEvent.OnPassClick)
                    })
                },
                keyboardOptions = KeyboardOptions().copy(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        eventHandler.invoke(MainEvent.OnButtonClick)
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(viewState.users, key = {it.email }){
            MyListItem(user = it){
                Log.e("User", it.toString())
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    eventHandler.invoke(MainEvent.OnButtonClick)
                }) {
                Text(text = stringResource(id = R.string.app_name))
            }
        }
    }
}

@Composable
fun MyListItem(
    user: User,
onClick: (User) -> Unit){
    Column(
        Modifier
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                onClick.invoke(user)
            }) {
        Text(text = "My name ${user.name}")
        Text(text = "My name ${user.email}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeProjectTheme {
        MainContent(
            viewState = MainViewState(
                title = "Test",
                email = "voda",
                isPassword = false,
                users = listOf(User("test","xdxd"))
            ), {}
        )
    }
}