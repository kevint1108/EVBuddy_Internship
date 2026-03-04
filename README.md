# ⚡ EV Buddy App

**EV Buddy** is a peer-to-peer electric vehicle charging platform that connects EV drivers with nearby fixed charging stations or mobile power providers (V2V charging on demand).

Built as part of an internship assignment to demonstrate modern Android development skills using **Kotlin** and **Jetpack Compose**.

---

## 📱 Screenshots

<p align="center">
  <img src="Homescreen EVBuddy App.png" width="22%" alt="Home Screen"/>
  &nbsp;
  <img src="My_request_screen_EVBuddy_app.png" width="22%" alt="My Requests"/>
  &nbsp;
  <img src="Profile #1 screen EVBuddy.png" width="22%" alt="Profile"/>
  &nbsp;
  <img src="Profile #2 screen EVBuddy.png" width="22%" alt="Settings"/>
</p>

<p align="center">
  <em>Home &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; My Requests &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Profile &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Settings</em>
</p>

---

## 🚀 Features

- 🔍 Find nearby **fixed charging stations** with real-time port availability
- 🚗 Request a **mobile power driver** for on-demand V2V charging
- 📍 Interactive map view with charger pin locations
- ⚡ Live battery & range status display
- 📋 Charging history & request tracking
- 👤 User profile with vehicle and payment management

---

## 🛠️ Tech Stack

| | |
|---|---|
| Language | Kotlin |
| UI Framework | Jetpack Compose + Material 3 |
| Architecture | MVVM (ViewModel + StateFlow) |
| Navigation | Navigation Compose |
| Min SDK | API 24 (Android 7.0) |
| Target SDK | API 36 |

---

## 📁 Project Structure

```
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
```

---

## 🔧 How to Run

1. Clone this repository
2. Open in **Android Studio**
3. Wait for Gradle sync to complete
4. Press **Run ▶** or `Shift + F10`
5. Select an emulator or connected device

---

## 👨‍💻 Author

**Kevin Tang** — EV Buddy Internship Assignment
