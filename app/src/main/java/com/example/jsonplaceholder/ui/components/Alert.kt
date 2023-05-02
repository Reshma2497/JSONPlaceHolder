package com.example.jsonplaceholder.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.log

@Composable
fun Alert(title:String,message:String,navigate:()->Unit) :Boolean{
    var alertStatus:Boolean=true
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                Button(onClick = { showDialog = false
                    alertStatus=true
                navigate()}) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false
                    alertStatus=false}) {
                    Text("Cancel")

                }
            }
        )
    }
    return  alertStatus

}
