package br.com.m2silva.compose

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import br.com.m2silva.domain.usecase.Customer
import coil.compose.SubcomposeAsyncImage


@Composable
fun Customers(customers: List<Customer>) {
    LazyColumn {
        items(customers) { item ->
            ItemCustomer(item)
        }
    }
}

@Composable
fun ItemCustomer(customer: Customer, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        elevation = 10.dp
    ) {
        ConstraintLayout(modifier = Modifier.padding(8.dp)) {

            val (image, dataColumn, tag) = createRefs()

            Column(modifier = Modifier
                .constrainAs(dataColumn) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 8.dp)) {
                TitleWithValue(titleWithValue = TitleWithValue("Id", customer.id))
                customer.name?.let { TitleWithValue(titleWithValue = TitleWithValue("Name", it)) }
                TitleWithValue(titleWithValue = TitleWithValue("Email", customer.email))
                customer.phone?.let { TitleWithValue(titleWithValue = TitleWithValue("Phone", it)) }
                customer.profileLink?.let {
                    TitleWithValue(titleWithValue = TitleWithValue("Profile", it)) {
                        val browserIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(customer.profileLink))
                        startActivity(context, browserIntent, null)
                    }
                }
            }

            SubcomposeAsyncImage(
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(tag.top)
                    }
                    .clickable { }
                    .size(64.dp)
                    .clip(CircleShape),
                contentDescription = "",
                model = customer.profileImage
            )

            Tag(status = customer.status, modifier = Modifier.constrainAs(tag) {
                top.linkTo(image.bottom)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        }
    }
}

@Composable
fun TitleWithValue(titleWithValue: TitleWithValue, click: (() -> Unit)? = null) {
    Column {
        Text(text = titleWithValue.title, fontWeight = FontWeight.Light)
        Text(
            text = titleWithValue.value,
            fontWeight = FontWeight.Bold,
            color = click?.let { Color.Blue } ?: run { Color.Black },
            modifier = Modifier.clickable {
                click?.let { click() }
            })
    }
}

@Composable
fun Tag(status: String, modifier: Modifier = Modifier) {
    Text(
        text = status,
        color = Color.Black,
        fontWeight = FontWeight.Medium,
        modifier = modifier
            .background(status.activeToColor(), shape = RoundedCornerShape(10.dp))
            .padding(all = 10.dp)
    )
}

data class TitleWithValue(val title: String, val value: String)

fun String.activeToColor(): Color =
    if (this.equals("active", true)) Color(0x6600FF00) else Color(0x66FFFF00)