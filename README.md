# DataBindingCleaner
DataBindingCleaner automatically frees memory

# Usage

### build.gradle
```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### app/build.gradle
```groovy
plugins {
  id 'org.jetbrains.kotlin.kapt'
}

android {
  buildFeatures {
    dataBinding true
  }
}

dependencies {
  implementation 'com.github.yass97:databinding-cleaner:1.0.1'
}
```

### Fragment
```kotlin
class SampleFragment : Fragment(R.layout.fragment_sample) {

    private val binding by autoReleaseBinding<FragmentSampleBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ...
    }

    companion object {
        fun newInstance() = SampleFragment()
    }
}
```

# Licence
```
Copyright (C) 2023 yass97

Licensed under the Apache License, Version 2.0
```
