# ?? ToDo List — Android App

Simple task management app with local storage using Room database.

## ?? Screenshots
*(Coming soon from GitHub Actions build)*

## ?? Tech Stack
- **Kotlin** + Coroutines
- **MVVM** Architecture
- **Room** Database (local storage)
- **LiveData** for reactive UI
- **ViewBinding** for layouts
- **RecyclerView** with DiffUtil

## ?? Project Structure
pp/src/main/java/com/example/todolist/  
+-- data/ — Room DAO, Database, Repository  
+-- model/ — Task entity  
L-- ui/ — MainActivity, TaskAdapter, TaskViewModel

## ?? How to Build
1. Open in **Android Studio** (Hedgehog or newer)  
2. Sync Gradle  
3. **Build > Build Bundle(s) / APK > Build APK**  

Or via CLI: ./gradlew assembleDebug

## ?? Download APK
See [Releases](https://github.com/Leonov792/ToDoList_Android/releases)
