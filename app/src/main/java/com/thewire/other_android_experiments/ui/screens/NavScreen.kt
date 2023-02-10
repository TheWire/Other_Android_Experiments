package com.thewire.other_android_experiments.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackextensions.servicesktx.add
import com.zhuinden.simplestackextensions.servicesktx.rebind
import kotlinx.parcelize.Parcelize


@Immutable
@Parcelize
data class NavKey(val title: String): ComposeKey() {
    constructor(): this("")

    @Composable
    override fun ScreenComposable(modifier: Modifier) {
        NavScreen("title")
    }

    override fun bindServices(serviceBinder: ServiceBinder) {
        with(serviceBinder) {
            val navModel = NavModel(backstack)

            add(navModel)
            rebind<NavScreen.ActionHandler>(navModel)
        }
    }

}
class NavModel(
    private val backstack: Backstack
): NavScreen.ActionHandler {
    override fun navigateToXML() {
        backstack.goTo(XMLKey())
    }
}

class NavScreen private constructor() {
    fun interface ActionHandler {
        fun navigateToXML()
    }

    companion object {
        @Composable
        @SuppressLint("ComposableNaming")
        operator fun invoke(title: String, modifier: Modifier = Modifier) {
            val eventHandler = rememberService<ActionHandler>()
            Column() {
                Text("nav screen")
                Button(
                    onClick = { eventHandler.navigateToXML() }
                ) {
                    Text("XML Screen")
                }
            }

        }
    }
}