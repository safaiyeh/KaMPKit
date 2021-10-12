package co.touchlab.kampkit

import co.touchlab.kermit.Logger
import co.touchlab.kermit.crashlytics.CrashlyticsLogWriter
import co.touchlab.kermit.crashlytics.setupCrashlyticsExceptionHook

fun setupKermit() {
    Logger.addLogWriter(CrashlyticsLogWriter())
    setupCrashlyticsExceptionHook(Logger)
}
