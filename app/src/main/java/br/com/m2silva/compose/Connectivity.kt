package br.com.m2silva.compose

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview
@Composable
fun NoConnectivity() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (icon, text) = createRefs()

        Icon(Icons.Filled.Warning, "", tint = Color(0X99E6CC00),
            modifier = Modifier
                .constrainAs(icon) {
                    linkTo(top = parent.top, bottom = text.top, bias = 1F)

                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(0.6f)
                .aspectRatio(1f)
        )

        Text(
            text = "No network available, please check your WiFi or Data connection",
            fontSize = 24.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(text) {
                linkTo(top = icon.bottom, topMargin = 16.dp, bottom = parent.bottom, bias = 0.0F)

                start.linkTo(icon.start)
                end.linkTo(icon.end)
            }
        )
    }
}