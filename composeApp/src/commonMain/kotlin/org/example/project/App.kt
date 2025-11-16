package org.example.project

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.database.AppDatabase
import org.example.project.database.TodoEntity
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    database: AppDatabase,
    viewModel: AppViewModel = viewModel { AppViewModel(database) }
) {
    val todos by viewModel.todos.collectAsState()
    val totalTodos by viewModel.totalTodos

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchTotalTodos()
        viewModel.fetchTodos()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .safeContentPadding()
    ) {
        Text(
            "You currently have $totalTodos TODOs",
            fontWeight = FontWeight.Companion.Black,
            fontSize = 20.sp
        )

        Spacer(Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black)
                .padding(10.dp)
        ) {
            Text(
                text = "Create TODO",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))


            Text("Title")

            BasicTextField(
                value = title,
                onValueChange = { title = it },
                Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black)
                    .padding(5.dp)
            )

            Spacer(Modifier.height(10.dp))

            Text("Content")

            BasicTextField(
                value = content,
                onValueChange = { content = it },
                Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black)
                    .padding(5.dp)
            )

            Spacer(Modifier.height(10.dp))

            Button(onClick = {
                viewModel.addTodo(
                    TodoEntity(
                        title = title,
                        content = content
                    )
                )

                title = ""
                content = ""

                viewModel.fetchTotalTodos()
            }) {
                Text("Save TODO")
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
        ) {
            Spacer(Modifier.height(20.dp))

            Text(
                "Your TODOs",
                fontWeight = FontWeight.Companion.Black,
                fontSize = 20.sp
            )

            todos.forEach {
                Text(
                    text = it.title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = it.content,
                )
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}