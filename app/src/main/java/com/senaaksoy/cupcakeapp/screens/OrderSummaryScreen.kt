package com.senaaksoy.cupcakeapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.senaaksoy.cupcakeapp.R
import com.senaaksoy.cupcakeapp.components.MytopAppBar
import com.senaaksoy.cupcakeapp.navigation.Screen

@Composable
fun OrderSummaryScreen(
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    flavor: String,
    date: String,
    subtotal: () -> String,
    onCancelButtonClicked: () -> Unit,
    onStartAnotherAppButtonClicked: () -> Unit,
    quantityText: String

) {

    Scaffold(
        topBar = {
            MytopAppBar(currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                navigateUp = { navigateUp() })
        }
    )
    { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = stringResource(id = R.string.quantity),
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = quantityText,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold
            )

            Divider(
                modifier = Modifier.padding(4.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(id = R.string.flavor),
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = flavor,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold
            )
            Divider(
                modifier = Modifier.padding(4.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(id = R.string.pickup_date),
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = date,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold
            )
            Divider(
                modifier = Modifier.padding(4.dp),
                thickness = 1.dp
            )
            Text(
                text = stringResource(R.string.subtotal, subtotal()),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onStartAnotherAppButtonClicked() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA04464))
            ) {
                Text(text = stringResource(id = R.string.send_order_to_another_app))
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onCancelButtonClicked() },
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = Color(0xFFA04464)
                )

            }


        }

    }


}