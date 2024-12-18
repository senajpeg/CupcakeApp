package com.senaaksoy.cupcakeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senaaksoy.cupcakeapp.R
import com.senaaksoy.cupcakeapp.components.MytopAppBar
import com.senaaksoy.cupcakeapp.navigation.Screen

@Composable
fun StartOrderScreen(
    onNextClick: (Int) -> Unit = {},
    navigateUp: () -> Unit={},
    currentScreen: Screen,
    canNavigateBack:Boolean
) {
    Scaffold(
        topBar = {
            MytopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                navigateUp = {navigateUp()}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cupcakeresmi),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(300.dp),
                )
                Text(
                    text = stringResource(id = R.string.order_cupcakes),
                    fontSize = 24.sp,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onNextClick(1) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA04464)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.one_cupcake))
            }
            Button(
                onClick = { onNextClick(6) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA04464)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.six_cupcakes))
            }
            Button(
                onClick = { onNextClick(12) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA04464)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.twelve_cupcakes))
            }


        }
    }
}

