import java.util.Properties
import java.io.FileInputStream
 <HEAD>

 <HEAD>
import org.gradle.api.tasks.Delete
import org.gradle.api.file.Directory

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
 7e307b1 (Sincroniza ajustes do Flutter e limpeza de arquivos de build)
 26db685 (Atualiza .gitignore e remove rastreamento de arquivos de build antigos)

plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

<HEAD>

<HEAD>
}


 7e307b1 (Sincroniza ajustes do Flutter e limpeza de arquivos de build)
 26db685 (Atualiza .gitignore e remove rastreamento de arquivos de build antigos)
val keystoreProperties = Properties()
val keystorePropertiesFile = rootProject.file("key.properties")
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

android {
    namespace = "com.patriciagiorgetto.yoma"
 <HEAD>
    compileSdk = 35

 <HEAD>
android {
    namespace = "com.example.yoma_app_novo"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion
 26db685 (Atualiza .gitignore e remove rastreamento de arquivos de build antigos)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    defaultConfig {
 <HEAD>
    applicationId = "com.patriciagiorgetto.yoma"
    minSdk = flutter.minSdkVersion
    targetSdk = 35
    versionCode = 4
    versionName = "1.0.4"
}


        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.patriciagiorgetto.yoma"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName

    compileSdk = 35

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
 7e307b1 (Sincroniza ajustes do Flutter e limpeza de arquivos de build)
    }
 26db685 (Atualiza .gitignore e remove rastreamento de arquivos de build antigos)

    kotlinOptions {
        jvmTarget = "1.8"
    }

    defaultConfig {
    applicationId = "com.patriciagiorgetto.yoma"
    minSdk = flutter.minSdkVersion
    targetSdk = 35
    versionCode = 4
    versionName = "1.0.4"
}


    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

flutter {
    source = "../.."
}
