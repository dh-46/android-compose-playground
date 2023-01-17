package com.dh46.compose_tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dh46.compose_tutorial.data.Message
import com.dh46.compose_tutorial.ui.theme.ComposetutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(msg = Message("Biden", "I'm the President of United States."))
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.img_sample),
            contentDescription = "Contact profile picture"
        )
        Column {
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    MessageCard(Message("Biden", "I'm the President of United States."))
}