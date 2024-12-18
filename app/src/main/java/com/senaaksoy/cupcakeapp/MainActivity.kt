package com.senaaksoy.cupcakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.senaaksoy.cupcakeapp.navigation.CupcakeNavigation
import com.senaaksoy.cupcakeapp.ui.theme.CupcakeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CupcakeAppTheme {
                CupcakeNavigation()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CupcakeNavigationPreview() {
    CupcakeAppTheme {
        CupcakeNavigation()
    }
}