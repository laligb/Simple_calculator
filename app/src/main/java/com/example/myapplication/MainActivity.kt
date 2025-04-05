package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {

                        MyForm()
                    }
                }

            }
        }
    }
}

@Composable
fun MyForm() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableIntStateOf(0) }
    val operations = listOf("+", "-", "*", "/")
    var selectedOperation by remember { mutableStateOf(operations[0]) }


    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 24.dp)
        ) {
            Text(
                text = "Calculator",
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = number1,
                onValueChange = { number1 = it },
                label = { Text("Number 1") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            TextField(
                value = number2,
                onValueChange = { number2 = it },
                label = { Text("Number 1") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))
            OperationSelector(
                operations = operations,
                selectedOperation = selectedOperation,
                onOperationSelected = { selectedOperation = it }
            )



            Button(
                onClick = {
                    val n1 = number1.toIntOrNull() ?: 0
                    val n2 = number2.toIntOrNull() ?: 0
                    result = when (selectedOperation) {
                        "+" -> n1 + n2
                        "-" -> n1 - n2
                        "*" -> n1 * n2
                        "/" -> if (n2 != 0) n1 / n2 else 0
                        else -> 0
                    }

                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text("Calculate")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Result: $result")
        }
    }
}

@Composable
fun OperationSelector(
    operations: List<String>,
    selectedOperation: String,
    onOperationSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = selectedOperation,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            operations.forEach { operation ->
                DropdownMenuItem(
                    text = { Text(operation) },
                    onClick = {
                        onOperationSelected(operation)
                        expanded = false
                    }
                )
            }
        }
    }
}
