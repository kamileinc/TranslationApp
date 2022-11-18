package com.example.translationapp.ui.translate

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.translationapp.R
import com.example.translationapp.utilities.Resource

@ExperimentalComposeUiApi
@Composable
fun TranslateScreen(translator: String, viewModel: TranslateViewModel) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.state.collectAsState()
    var text by remember {
        mutableStateOf("")
    }

    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        Text(text = stringResource(R.string.translator, translator.uppercase()))
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                viewModel.getTranslation(translator, text)
                keyboardController?.hide()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = stringResource(R.string.translate))
        }
        Spacer(modifier = Modifier.height(8.dp))
        when (state) {
            is Resource.Success -> {
                OutlinedTextField(
                    value = state.data.toString(),
                    enabled = false,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledTextColor = MaterialTheme.colors.onSurface,
                        disabledBorderColor = MaterialTheme.colors.primary,
                    )
                )
            }
            is Resource.Empty -> {
                OutlinedTextField(
                    value = "",
                    enabled = false,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledTextColor = MaterialTheme.colors.onSurface,
                        disabledBorderColor = MaterialTheme.colors.primary,
                    )
                )
            }
            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                LaunchedEffect(state) {
                    Toast.makeText(
                        context,
                        state.message?.asString(context),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }
}
