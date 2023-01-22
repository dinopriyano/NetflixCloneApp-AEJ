import org.gradle.api.invocation.Gradle
import java.io.File
import java.util.Properties

object ConfigData {

  const val compileSdk = 33
  const val minSdk = 21
  const val targetSdk = 33

  fun getAppVersion(versionPropsFile: File, gradle: Gradle): Pair<Int, String> {
    val versionProps = Properties()
    if(versionPropsFile.canRead()) {
      versionProps.load(versionPropsFile.inputStream())
    }
    else {
      versionProps["VERSION_CODE"] = "1"
    }

    var incrementNumber = 0
    val runTasks = gradle.startParameter.taskNames
    if("assemble" in runTasks || "assembleRelease" in runTasks || "aR" in runTasks || "bundleRelease" in runTasks || ":app:bundleRelease" in runTasks) {
      incrementNumber = 1
    }

    val verCode = versionProps["VERSION_CODE"].toString().toInt() + incrementNumber
    // for the ex. 121
    val verFirst = verCode / 100 // 121 / 100 = 1
    val verSecond = (verCode - (100 * verFirst)) / 10 // (121 - (100 * 1)) / 10 = 2
    val verThird = (verCode -  (100 * verFirst) - (10 * verSecond)) // (121 - (100 * 1) - (10 * 2)) = 1
    val verName = "$verFirst.$verSecond.$verThird" // result is 1.2.1
    versionProps["VERSION_CODE"] = verCode.toString()
    versionProps.store(versionPropsFile.writer(), null)

    return Pair(verCode, verName)
  }

}