package com.example.lab8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab8.ui.theme.Lab8Theme

class MainActivity : ComponentActivity() {
    private var counterPtr: Long = 0
    private var counterValue: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        counterPtr = createCounter()
        counterValue = createCounterWithValue(7)

        setContent{
            Lab8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val modifier = Modifier.padding(innerPadding)

                    NavHost(navController = navController, startDestination = Routes.MainMenu) {
                        composable(Routes.MainMenu){ Menu(modifier, navController) }
                        composable(Routes.TaskCounter) {
                            Counter().CounterApp(
                                counterPtr = counterPtr,
                                counterValue = counterValue,
                                modifier = modifier
                            )
                        }
                        composable(Routes.TaskStringList) {
                            StringList().StringListApp(modifier = modifier)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyCounter(counterPtr)
        destroyCounter(counterValue)
    }

    private external fun createCounter(): Long
    private external fun createCounterWithValue(value: Int) : Long
    private external fun destroyCounter(counterPtr: Long)

    companion object {
        // Used to load the 'lab8' library on application startup.
        init {
            System.loadLibrary("lab8")
        }
    }
}

@Composable
fun Menu(modifier: Modifier = Modifier, navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.DarkGray
            ),
            onClick = { navController.navigate(Routes.TaskCounter) },
        ) {
            Text(text = "Counter", fontSize = 30.sp)
        }

        Button(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.DarkGray
            ),
            onClick = { navController.navigate(Routes.TaskStringList) },
        ) {
            Text(text = "String List", fontSize = 30.sp)
        }
    }
}