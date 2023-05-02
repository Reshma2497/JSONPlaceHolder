package com.example.jsonplaceholder.ui.todos

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jsonplaceholder.data.model.todos.TodosModelItemModel
import com.example.jsonplaceholder.ui.components.SearchBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Todos(navController: NavHostController, viewModel: TodosViewModel = hiltViewModel()) {
    val todos by viewModel.todos.collectAsState()
    val searchQuery = remember { mutableStateOf("") }
    val filteredTodosList = if (searchQuery.value.isBlank()) {
        todos
    } else {

        todos.filter { todos ->
            todos.title!!.contains(searchQuery.value, ignoreCase = true) ||
            todos.userId!!.toString().equals(searchQuery.value, ignoreCase = true)
        }
    }
    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SearchBar(searchQuery,"Search By Todos Title or User Id")


       ListTodos(filteredTodosList = filteredTodosList)

        }
    }
}

@Composable
fun ListTodos(filteredTodosList:List<TodosModelItemModel>)
{
    LazyColumn(
        modifier = Modifier
            .padding(top = 10.dp)

    ) {
        itemsIndexed(filteredTodosList) { _, todo->
            //AddTodos(todos)
            TodoItem(todo = todo)
            Divider(color = Color.LightGray, thickness = 1.dp)
        }

    }
}



@Composable
fun AddTodos(model: TodosModelItemModel) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 5.dp, horizontal = 10.dp)
//            .clickable {
//                onNextClick(model)
//            },
        //  verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "${model.userId}",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${model.id}",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${model.title}",
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TodoItem(todo: TodosModelItemModel) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = todo.title ?: "",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                    .weight(0.8f)
                    .padding(end = 4.dp)
                )
                Checkbox(
                    checked = todo.completed ?: false,
                    onCheckedChange = {},
                    modifier = Modifier
                        .weight(0.2f)
                        .padding(start = 4.dp)
                   // modifier = Modifier.padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "User ID: ${todo.userId ?: 0}",
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}
