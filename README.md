⚡ EV Buddy App
EV Buddy is a peer-to-peer electric vehicle charging platform that connects
EV drivers with nearby fixed charging stations or mobile power providers
(V2V charging on demand).
Built as part of an internship assignment to demonstrate modern Android
development skills using Kotlin and Jetpack Compose.

📱 Screenshots
HomeMy RequestsProfileSettingsShow ImageShow ImageShow ImageShow Image

🚀 Features

🔍 Find nearby fixed charging stations with real-time port availability
🚗 Request a mobile power driver for on-demand V2V charging
📍 Interactive map view with charger pin locations
⚡ Live battery & range status display
📋 Charging history & request tracking
👤 User profile with vehicle and payment management


🛠️ Tech Stack
LanguageKotlinUI FrameworkJetpack Compose + Material 3ArchitectureMVVM (ViewModel + StateFlow)NavigationNavigation ComposeMin SDKAPI 24 (Android 7.0)Target SDKAPI 36

📁 Project Structure
com.example.evbuddy/
├── MainActivity.kt              # App entry point + navigation
├── EVBuddyViewModel.kt          # State management (StateFlow)
├── data/
│   └── Models.kt                # Data classes + MockDataRepository
└── ui/
    ├── Navigation.kt            # Route definitions
    ├── theme/
    │   └── Color.kt             # Brand colors
    └── screens/
        ├── HomeScreen.kt        # Main screen
        ├── MyRequestsScreen.kt  # Charging history
        └── ProfileScreen.kt     # User profile

🔧 How to Run

Clone this repository
Open in Android Studio
Wait for Gradle sync to complete
Press Run ▶ or Shift + F10
Select an emulator or connected device


👨‍💻 Author
Kevin Tang — EV Buddy Internship Assignment
