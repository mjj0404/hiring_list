# Hiring List for FetchRewards

Fetch dummy hiring list received from FetchRewards to RecyclerView


## Structure
```
app<br/>
├── api<br/>
│   └── RetrofitService<br/>
├── model<br/>
│   └── HiringItem<br/>
├── ui<br/>
│   ├── list<br/>
│   │   ├── HiringListAdapter<br/>
│   │   ├── HiringListFragment<br/>
│   │   ├── HiringListRepository<br/>
│   │   └── HiringListViewModel<br/>
│   ├── setting<br/>
│   │   └── SettingsFragment<br/>
│   └── utility<br/>
│       ├── Constant<br/>
│       ├── HiringListApplication<br/>
│       └── PreferenceManager<br/>
└── MainActivity<br/>
```
## Components/Additional Libraries used
```
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1"
implementation 'androidx.core:core-splashscreen:1.0.0'
implementation 'androidx.navigation:navigation-fragment-ktx:2.5.2'
implementation 'androidx.navigation:navigation-ui-ktx:2.5.2'
```
## Additional features
SearchView and text query filter in Adapter class</br>
Icons and Themes
