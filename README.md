# ⚡ EV Buddy App

**EV Buddy** is a peer-to-peer electric vehicle charging platform that connects EV drivers with nearby fixed charging stations or mobile power providers (V2V charging on demand).

Built as part of an internship assignment to demonstrate modern Android development skills using **Kotlin** and **Jetpack Compose**.

---

## 📱 Screenshots

<p align="center">
 <img width="322" height="542" alt="Screenshot 2026-03-04 153735" src="https://github.com/user-attachments/assets/14884a62-5a5c-47fe-bbcd-753cc44d2275" />
<img width="328" height="738" alt="Screenshot 2026-03-04 153707" src="https://github.com/user-attachments/assets/34b63064-2c6d-4ff4-b579-5cc1ccbe524d" />
<img width="329" height="736" alt="Screenshot 2026-03-04 153643" src="https://github.com/user-attachments/assets/5c901f9c-33b2-4ef7-a915-2aa9fd587eb5" />
<img width="332" height="735" alt="Screenshot 2026-03-04 153612" src="https://github.com/user-attachments/assets/7b80d3e4-84b9-4d0f-af6b-d4a44f81dfc6" />

</p>

<p align="center">
  <em>Home &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; My Requests &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Profile &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Settings</em>
</p>

---

## 🚀 Features

- 🔍 Find nearby **fixed charging stations** with real-time port availability
- 🚗 Request a **mobile power driver** for on-demand V2V charging
- 🗺️ Interactive map view powered by **OpenStreetMap** (no API key required)
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
| Map | OSMDroid (OpenStreetMap) |
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
        ├── HomeScreen.kt        # Main screen + OSMDroid map
        ├── MyRequestsScreen.kt  # Charging history
        └── ProfileScreen.kt     # User profile & settings
```

---

## 🔧 How to Run

1. Clone this repository
2. Open in **Android Studio**
3. Wait for Gradle sync to complete
4. Press **Run ▶** or `Shift + F10`
5. Select an emulator or connected device

> ✅ No API key needed — map uses OpenStreetMap (free & open source)

---

## 👨‍💻 Author

**Kevin Tang** — EV Buddy Internship Assignment
