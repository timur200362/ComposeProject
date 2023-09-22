package com.example.composeproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.composeproject.ui.theme.ComposeProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectTheme {
                // A surface container using the 'background' color from the theme
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AnimeLayout("Android",
                        modifier= Modifier
                            .background(color = Color.Red))
                }
            }
        }
    }
}

@Composable
fun AnimeLayout(
    name: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier){
        Text(text = "Hello $name!")
        TextField(
            value = "Test",
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {},
            enabled = false,
            readOnly = false,
            label = {},
            placeholder = {},
            leadingIcon = {
                Box(modifier = Modifier.size(16.dp)){
                    Image(
                        painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null)
                }
            },
            trailingIcon = {},
            supportingText = {},
            isError = false,
            keyboardOptions = KeyboardOptions().copy(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                },
                onSearch = {

                }
            ),
            singleLine = false,
            maxLines = 10
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
            Log.e("Button", "Hello!")
        }) {
            Text(text = stringResource(id = R.string.app_name))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeProjectTheme {
        AnimeLayout("Android",
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red))
    }
}