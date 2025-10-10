package me.twc.ktils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.twc.kpose.rememberLauncherForActivityResultAwait
import me.twc.ktils.core.ext.showShortToast
import me.twc.ktils.ui.theme.KtilsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtilsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val launcher = rememberLauncherForActivityResultAwait(SecondActivity.Contract())
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding).clickable{
                            lifecycleScope.launch(Dispatchers.Main) {
                                val result = launcher.awaitLaunch(Unit)
                                showShortToast(result)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KtilsTheme {
        Greeting("Android")
    }
}