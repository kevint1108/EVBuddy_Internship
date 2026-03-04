package com.example.evbuddy.data

data class MobileDriver(
    val id: Int,
    val name: String,
    val distanceKm: Double,
    val etaMinutes: Int,
    val rating: Float,
    val batteryCapacityKwh: Int,
    val isAvailable: Boolean = true
)

data class FixedCharger(
    val id: Int,
    val name: String,
    val address: String,
    val distanceKm: Double,
    val powerKw: Int,
    val availablePorts: Int,
    val totalPorts: Int,
    val pricePerKwh: Double
)

object MockDataRepository {

    val mobileDrivers = listOf(
        MobileDriver(1, "Nguyen Van A", 1.2, 5, 4.9f, 60),
        MobileDriver(2, "Tran Thi B", 2.5, 10, 4.7f, 80),
        MobileDriver(3, "Le Van C", 3.8, 15, 4.5f, 50),
        MobileDriver(4, "Pham Thi D", 4.1, 18, 4.8f, 100),
        MobileDriver(5, "Hoang Van E", 5.6, 22, 4.3f, 75)
    )

    val fixedChargers = listOf(
        FixedCharger(1, "EV Station - Quan 1", "123 Nguyen Hue, Q.1", 0.8, 50, 3, 6, 3500.0),
        FixedCharger(2, "VinFast Charging Hub", "456 Le Lai, Q.1", 1.5, 150, 5, 8, 4000.0),
        FixedCharger(3, "Aeon Mall Charger", "30 Bo Bao Tan Thang, Q.Tan Phu", 3.2, 22, 2, 4, 3200.0)
    )
}
