package com.dh46.compose_tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dh46.compose_tutorial.data.Message
import com.dh46.compose_tutorial.data.SampleData
import com.dh46.compose_tutorial.ui.theme.ComposetutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposetutorialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(messages = SampleData.conversationSample)
//                    MessageCard(msg = Message("Biden", "I'm the President of United States."))
                }
            }
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
                // Add border to Image
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        // Add vertical space to Image and Texts (like margin)
        Spacer(modifier = Modifier.width(8.dp))

        // Keep track if the message is expanded or not in this variable
        var isExpanded by remember {
            mutableStateOf(false)
        }

        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            // Add horizontal space to Author text and body text
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // set surfaceColor here it will change gradually
                color = surfaceColor,
                // animateContentSize will change padding gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

//@Preview(name = "Light Mode")
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
//@Composable
//fun PreviewMessageCard() {
//    ComposetutorialTheme {
//        Surface(modifier = Modifier.fillMaxSize()) {
//            MessageCard(msg = Message("Biden", "I'm the President of United States."))
//        }
//    }
//}

@Composable
fun Conversation(messages: List<Message>) {

    LazyColumn {
        items(messages) { message ->
            // Here will be called for each item in the messages list
            MessageCard(msg = message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    ComposetutorialTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}