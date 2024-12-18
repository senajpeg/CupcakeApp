package com.senaaksoy.cupcakeapp.navigation

import androidx.annotation.StringRes
import com.senaaksoy.cupcakeapp.R

enum class Screen(val route: String, @StringRes val title: Int) {
    STARTORDER("startorder", title = R.string.app_name),
    SELECTFLAVOR("selectflavor", title = R.string.choose_flavor),
    SELECTPICKUPDATE("selectpickupdate", title = R.string.choose_pickupDate),
    ORDERSUMMARY("ordersummary", title = R.string.order_summary)
}
