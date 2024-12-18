package com.senaaksoy.cupcakeapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senaaksoy.cupcakeapp.R
import com.senaaksoy.cupcakeapp.components.MytopAppBar
import com.senaaksoy.cupcakeapp.navigation.Screen

@Composable
fun SelectFlavorScreen(
    subtotal: ()-> String,
    options: List<String>,
    onSelectionChanged: (String) -> Unit ,
    onNextButtonClicked: () -> Unit ,
    onCancelButtonClicked: () -> Unit ,
    navigateUp: () -> Unit = {},
    flavor: String = "",
    currentScreen: Screen,
    canNavigateBack: Boolean
) {
    var selectedFlavor by rememberSaveable { mutableStateOf(flavor) }

    Scaffold(
        topBar = {
            MytopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canNavigateBack,
                navigateUp = { navigateUp() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            options.forEach { flavor ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = selectedFlavor == flavor,
                            onClick = {
                                selectedFlavor = flavor
                                onSelectionChanged(flavor)
                            }
                        )
                        .padding(start = 4.dp, top = 4.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedFlavor == flavor,
                        onClick = {
                            selectedFlavor = flavor
                            onSelectionChanged(flavor)
                        },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFA04464))
                    )
                    Text(
                        text = flavor,
                        modifier = Modifier.padding(start = 2.dp),
                        fontSize = 18.sp
                    )
                }
            }
            Divider(
                modifier = Modifier.padding(16.dp),
                thickness = 1.dp,
            )
            Text(
                text = stringResource(R.string.subtotal, subtotal()),
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedButton(
                    onClick = { onCancelButtonClicked() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = Color(0xFFA04464))
                }
                Spacer(modifier = Modifier.weight(0.1f))
                Button(
                    onClick = { onNextButtonClicked() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA04464)),
                    enabled = selectedFlavor.isNotEmpty()
                ) {
                    Text(text = stringResource(id = R.string.next))
                }
            }
        }
    }
}

