package com.minroud.mortyverse.ui.navigation.drawer

import androidx.compose.material3.DrawerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface DrawerController {
    fun open()

    fun close()
}

class DrawerStateController(
    private val drawerState: DrawerState,
    private val coroutineScope: CoroutineScope,
) : DrawerController {
    override fun open() {
        coroutineScope.launch { drawerState.open() }
    }

    override fun close() {
        coroutineScope.launch { drawerState.close() }
    }
}
