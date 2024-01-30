package uk.ac.aber.dcs.cs31620.focab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.focab.ui.navigation.MainNavGraph
import uk.ac.aber.dcs.cs31620.focab.ui.theme.FocabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FocabTheme() {
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)) {
                    val navController = rememberNavController()
                    MainNavGraph()
                }
//
//                val navController = rememberNavController()
//                MainNavGraph()
            }
        }
    }
}
