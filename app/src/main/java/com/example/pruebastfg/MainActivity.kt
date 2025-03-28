package com.example.pruebastfg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pruebastfg.data.storage.AppsProtoRepository
import com.example.pruebastfg.data.storage.PreferencesRepository
import com.example.pruebastfg.data.storage.ViewModelFactory
import com.example.pruebastfg.data.storage.appsDataStore
import com.example.pruebastfg.ui.AppViewModel
import com.example.pruebastfg.ui.MainScreen
import com.example.pruebastfg.ui.theme.PruebasTFGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val context = LocalContext.current
            val prefsRepo = remember { PreferencesRepository(context) }
            val appsRepo = remember { AppsProtoRepository(context,context.appsDataStore) }
            val viewModel: AppViewModel = viewModel(factory = ViewModelFactory(prefsRepo, appsRepo, context))

            PruebasTFGTheme(viewModel) {
                MainScreen(
                    { packageName ->
                        val intent = packageManager.getLaunchIntentForPackage(packageName)
                        startActivity(intent)
                    },
                    viewModel = viewModel,
                    context = context
                )
            }
        }
    }
}
