package com.dh46.compose_tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dh46.compose_tutorial.data.Message

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
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.img_sample),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // set ImageSize to 40 dp
                .size(40.dp)
                // Clipped image to be shaped as a circle
                .clip(CircleShape)
        )

        // Add vertical space to Image and Texts (like margin)
        Spacer(modifier = Modifier.width(8.dp))
        
        Column {
            Text(text = msg.author)
            // Add horizontal space to Author text and body text
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    MessageCard(Message("Biden", "I'm the President of United States."))
}