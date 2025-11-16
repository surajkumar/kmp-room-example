package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.database.getDatabaseBuilder

fun MainViewController() {
    val database = getDatabaseBuilder().build()

    ComposeUIViewController {
        App(database = database)
    }
}