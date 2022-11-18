package com.example.translationapp.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.translationapp.R
import com.example.translationapp.ui.Screen

@Composable
fun MainScreen(navController: NavController) {
    MainScreenContent(navController)
}

@Composable
fun MainScreenContent(navController: NavController) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Title()
        TranslatorsList(
            listOf(
                "yoda", "pirate", "minion", "doge", "emoji", "braille", "numbers", "wow", "sith",
                "morse", "shakespeare", "oldenglish", "post-modern", "wheel-of-time-old-tongue",
                "leetspeak"
            ),
            navController)
    }
}

@Composable
fun Title() {
    Text(
        text = stringResource(R.string.select),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    Divider()
}

@Composable
fun TranslatorsList(
    translators: List<String>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = translators) {
            Translator(it, navController)
            Divider()
        }
    }
}

@Composable
fun Translator(translator: String, navController: NavController) {
    var isSelected by remember {
        mutableStateOf(false)
    }

    val targetColor by animateColorAsState(
        targetValue = if(isSelected) MaterialTheme.colors.primary else Color.Transparent,
    )

    Surface(color = targetColor) {
        Text(
            text = translator.uppercase(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable {
                    isSelected = !isSelected
                    navController.navigate(Screen.TranslateScreen.withArgs(translator))
                }
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}
