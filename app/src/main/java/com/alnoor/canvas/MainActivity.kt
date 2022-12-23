package com.alnoor.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.alnoor.canvas.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                    contentAlignment = Alignment.Center){

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .align(Alignment.BottomCenter)
                    )

                }
            }
        }
    }
}


@Composable
fun Card(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {

        val (title, progress, details) = createRefs()

        Text(
            text = "Tasks",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(title){
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 32.dp)
            }
        )

        CircularProgress(
            modifier = Modifier
                .size(100.dp)
                .constrainAs(progress) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(title.bottom)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
            values = {
                ProgressValues(
                    primary = 60f,
                    secondary = 75f
                )
            },
            maxValue = 100f,
            strokeWidth = 35f
        )

        Column(
            modifier = Modifier.constrainAs(details) {
                top.linkTo(progress.top, 16.dp)
                bottom.linkTo(progress.bottom, 16.dp)
                start.linkTo(progress.end, 16.dp)
                end.linkTo(parent.end, 16.dp)

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            verticalArrangement = Arrangement.SpaceAround
        ) {
            DetailsItem(color = MaterialTheme.colorScheme.primary, text = "Finish on time")
            Spacer(modifier = Modifier.height(8.dp))
            DetailsItem(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f), text = "Post the deadline")
        }
    }
}

@Composable
fun DetailsItem(modifier: Modifier = Modifier, color: Color, text: String) {
    Row (modifier = modifier, verticalAlignment = Alignment.CenterVertically){
       Surface(
           modifier = Modifier.size(25.dp),
           color = color
       ) {}

       Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color = Color.Black.copy(alpha = 0.7f),
            style = MaterialTheme.typography.titleMedium
        )
    }
}