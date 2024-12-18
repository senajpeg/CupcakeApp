package com.senaaksoy.cupcakeapp.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senaaksoy.cupcakeapp.data.DataSource
import com.senaaksoy.cupcakeapp.screens.SelectFlavorScreen
import com.senaaksoy.cupcakeapp.screens.StartOrderScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.senaaksoy.cupcakeapp.R
import com.senaaksoy.cupcakeapp.screens.OrderSummaryScreen
import com.senaaksoy.cupcakeapp.screens.SelectPickupDateScreen
import com.senaaksoy.cupcakeapp.viewmodel.CupcakeViewModel

@Composable
fun CupcakeNavigation(
    viewModel: CupcakeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        Screen.valueOf(backStackEntry?.destination?.route?.uppercase() ?: Screen.STARTORDER.name)


    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = Screen.STARTORDER.route) {
        composable(route = Screen.STARTORDER.route) {
            StartOrderScreen(
                onNextClick = { quantity ->
                    viewModel.upDateSubTotal(0)
                    viewModel.upDateSubTotal(quantity)
                    navController.navigate(Screen.SELECTFLAVOR.route)
                },
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null
            )
        }
        composable(route = Screen.SELECTFLAVOR.route) {
            SelectFlavorScreen(
                options = DataSource.flavors.map { stringResource(id = it) },
                subtotal = { viewModel.formatTotalPrice() },
                onSelectionChanged = { viewModel.upDateFlavor(it) },
                onNextButtonClicked = { navController.navigate(Screen.SELECTPICKUPDATE.route) },
                onCancelButtonClicked = {
                    navController.navigate(Screen.STARTORDER.route)
                    viewModel.resetOrder()
                },
                flavor = uiState.flavor,
                navigateUp = { navController.navigateUp() },
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
            )
        }
        composable(route = Screen.SELECTPICKUPDATE.route) {
            SelectPickupDateScreen(
                currentScreen = currentScreen,
                pickUpdate = uiState.pickupDate,
                canNavigateBack = navController.previousBackStackEntry != null,
                currentDateList = { viewModel.currentDateList() },
                navigateUp = {
                    navController.navigateUp()
                    viewModel.resetPickupDate()
                },
                onDateSelected = {
                    viewModel.upDatePickupDate(it)
                },
                subtotal = { viewModel.formatTotalPrice() },
                onCancelButtonClicked = {
                    navController.navigate(Screen.STARTORDER.route)
                    viewModel.resetOrder()
                },
                onNextButtonClicked = { navController.navigate(Screen.ORDERSUMMARY.route) }
            )
        }
        composable(route = Screen.ORDERSUMMARY.route) {
            val context = LocalContext.current

            val quantityText = "${uiState.quantity} " +
                    if (uiState.quantity == 1) "cupcake" else "cupcakes"
            OrderSummaryScreen(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                flavor = uiState.flavor,
                subtotal = { viewModel.formatTotalPrice() },
                date = uiState.pickupDate,
                quantityText = quantityText,
                onCancelButtonClicked = {
                    navController.navigate(Screen.STARTORDER.route)
                    viewModel.resetOrder()
                },
                onStartAnotherAppButtonClicked = {
                    val subject = context.getString(R.string.new_cupcake_order)
                    val summary = """
                        Quantity: $quantityText
                        Flavor: ${uiState.flavor}
                        Pickup Date: ${uiState.pickupDate}
                        Subtotal: ${viewModel.formatTotalPrice()}
                    """.trimIndent()

                    shareOrder(context, subject, summary)
                }
            )
        }

    }


}

fun shareOrder(context: Context, subject: String, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent, context.getString(R.string.new_cupcake_order)
        )
    )
}