apply plugin: 'com.android.application'

android {
    compileSdkVersion 34

    defaultConfig {
        applicationId "com.patricia.yoma"
        minSdkVersion 21
        targetSdkVersion 34
        versionCode 1
        versionName "1.0"
    }

    signingConfigs {
        release {
            storeFile file("C:/Users/Paty/Desktop/yoma_novo/my-release-key.jks")  // Caminho para o keystore
            storePassword "yoma2025"  // Senha do keystore
            keyAlias "mykey"  // Alias correto
            keyPassword "yoma2025"  // Senha da chave
        }
    }

    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled false
            shrinkResources false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    flavorDimensions "default"
    productFlavors {
        // Adicione sabores de build (se necessário)
    }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    // Outras dependências aqui
}
