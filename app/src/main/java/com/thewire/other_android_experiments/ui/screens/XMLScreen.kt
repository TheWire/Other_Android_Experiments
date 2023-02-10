package com.thewire.other_android_experiments.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.thewire.other_android_experiments.ui.viewmodels.XMLViewModel
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackcomposeintegration.services.rememberService
import com.zhuinden.simplestackextensions.servicesktx.add
import com.zhuinden.simplestackextensions.servicesktx.lookup
import com.zhuinden.simplestackextensions.servicesktx.rebind
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class XMLKey(val title: String): ComposeKey() {
    constructor(): this("")

    @Composable
    override fun ScreenComposable(modifier: Modifier) {
        val viewModel = rememberService<XMLViewModel>()
        XMLScreen("title", someVal =  viewModel.someValue)
    }

    override fun bindServices(serviceBinder: ServiceBinder) {
        with(serviceBinder) {
            val xmlModel = XMLModel(backstack)

            add(xmlModel)
            add(XMLViewModel(lookup<Int>("XML_INT")))
            rebind<XMLScreen.ActionHandler>(xmlModel)
        }
    }

}
class XMLModel(
    private val backstack: Backstack
): XMLScreen.ActionHandler {
    override fun navigateToNav() {
        backstack.goTo(NavKey())
    }
}

class XMLScreen private constructor() {
    fun interface ActionHandler {
        fun navigateToNav()
    }

    companion object {
        @Composable
        @SuppressLint("ComposableNaming")
        operator fun invoke(title: String, someVal: Int, modifier: Modifier = Modifier) {
            val eventHandler = rememberService<ActionHandler>()
            Text("xml screen $title $someVal")
        }
    }
}