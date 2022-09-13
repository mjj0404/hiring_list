## Hiring List for FetchRewards

Fetch dummy hiring list received from FetchRewards to RecyclerView


# Structure
app
├── api
│   └── RetrofitService
├── model
│   └── HiringItem
├── ui
│   ├── list
│   │   ├── HiringListAdapter
│   │   ├── HiringListFragment
│   │   ├── HiringListRepository
│   │   └── HiringListViewModel
│   ├── setting
│   │   └── SettingsFragment
│   └── utility
│       ├── Constant
│       ├── HiringListApplication
│       └── PreferenceManager
└── MainActivity

# Components/Additional Libraries used
 '''
 implementation 'androidx.core:core-ktx:1.9.0'
    implementation 'androidx.appcompat:appcompat:1.5.1'
    implementation 'com.google.android.material:material:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.preference:preference:1.2.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'

    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1"

    implementation 'androidx.core:core-splashscreen:1.0.0'

    implementation 'androidx.navigation:navigation-fragment-ktx:2.5.2'
    implementation 'androidx.navigation:navigation-ui-ktx:2.5.2'
    implementation 'androidx.navigation:navigation-dynamic-features-fragment:2.5.2'
  '''
