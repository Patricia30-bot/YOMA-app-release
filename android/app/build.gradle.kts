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


        applicationId = "com.patriciagiorgetto.yoma"
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
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

flutter {
    source = "../.."
}
