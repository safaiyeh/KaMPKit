package co.touchlab.kampkit.android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import co.touchlab.kampkit.AppInfo
import co.touchlab.kampkit.initKoin
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.crashlytics.CrashlyticsLogWriter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.addLogWriter(CrashlyticsLogWriter(minSeverity = Severity.Debug))

        initKoin(
            module {
                single<Context> { this@MainApp }
                viewModel { BreedViewModel() }
                single<SharedPreferences> {
                    get<Context>().getSharedPreferences("KAMPSTARTER_SETTINGS", Context.MODE_PRIVATE)
                }
                single<AppInfo> { AndroidAppInfo }
                single {
                    { Logger.withTag("Startup").i("Hello from Android/Kotlin!") }
                }
            }
        )
    }
}

object AndroidAppInfo : AppInfo {
    override val appId: String = BuildConfig.APPLICATION_ID
}
