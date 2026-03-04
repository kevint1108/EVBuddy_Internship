package com.example.evbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evbuddy.EVBuddyViewModel
import com.example.evbuddy.data.FixedCharger
import com.example.evbuddy.data.MobileDriver
import com.example.evbuddy.ui.theme.*

// ── Home Screen ───────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: EVBuddyViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item { EVBuddyHeader() }
            item { StatusCard() }
            item {
                Spacer(Modifier.height(20.dp))
                SectionTitle("What do you need?")
                Spacer(Modifier.height(12.dp))
                ActionButtonsRow(
                    onFindCharger = { viewModel.onFindFixedChargerClicked() },
                    onFindDriver = { viewModel.onFindMobileDriverClicked() }
                )
            }
            item {
                Spacer(Modifier.height(20.dp))
                SectionTitle("Nearby Stations")
                Spacer(Modifier.height(12.dp))
                MapPlaceholder()
            }
            item {
                Spacer(Modifier.height(20.dp))
                SectionTitle("Quick Tips")
                Spacer(Modifier.height(12.dp))
                QuickTipsCard()
                Spacer(Modifier.height(8.dp))
            }
        }

        // Mobile Driver Bottom Sheet
        if (uiState.showDriverSheet) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.dismissDriverSheet() },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
            ) {
                MobileDriverSheet(
                    drivers = uiState.mobileDrivers,
                    selectedDriver = uiState.selectedDriver,
                    onDriverSelected = { viewModel.onDriverSelected(it) },
                    onDismiss = { viewModel.dismissDriverSheet() }
                )
            }
        }

        // Fixed Charger Bottom Sheet
        if (uiState.showChargerSheet) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.dismissChargerSheet() },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
            ) {
                FixedChargerSheet(
                    chargers = uiState.fixedChargers,
                    onDismiss = { viewModel.dismissChargerSheet() }
                )
            }
        }
    }
}

// ── Header ────────────────────────────────────────────────────────────────────

@Composable
fun EVBuddyHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(EVGreenDark, EVGreen)))
            .padding(horizontal = 20.dp, vertical = 28.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        "⚡ EV Buddy",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        "Charge On Demand",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White.copy(alpha = 0.2f), CircleShape)
                    ) {
                        Icon(Icons.Filled.Notifications, null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White.copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("JD", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.2f))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Icon(Icons.Outlined.LocationOn, null, tint = Color.White, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Ho Chi Minh City, Vietnam", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.weight(1f))
                Icon(Icons.Filled.KeyboardArrowDown, null, tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }
    }
}

// ── Status Card ───────────────────────────────────────────────────────────────

@Composable
fun StatusCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .offset(y = (-16).dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatusItem(Icons.Filled.ElectricCar, "EV Range", "248 km", EVGreen)
            VerticalDivider(modifier = Modifier.height(48.dp))
            StatusItem(Icons.Filled.BatteryChargingFull, "Battery", "72%", EVBlue)
            VerticalDivider(modifier = Modifier.height(48.dp))
            StatusItem(Icons.Filled.Bolt, "Nearby", "12 pts", WarningAmber)
        }
    }
}

@Composable
fun StatusItem(icon: ImageVector, label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, null, tint = color, modifier = Modifier.size(24.dp))
        Spacer(Modifier.height(4.dp))
        Text(value, fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Text(label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

// ── Action Buttons ────────────────────────────────────────────────────────────

@Composable
fun ActionButtonsRow(onFindCharger: () -> Unit, onFindDriver: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ActionCard(
            modifier = Modifier.weight(1f),
            emoji = "🔍",
            label = "Find Fixed\nCharger",
            gradientColors = listOf(EVGreenDark, EVGreen),
            onClick = onFindCharger
        )
        ActionCard(
            modifier = Modifier.weight(1f),
            emoji = "🚗",
            label = "Find Mobile\nPower Driver",
            gradientColors = listOf(EVBlueDark, EVBlue),
            onClick = onFindDriver
        )
    }
}

@Composable
fun ActionCard(
    modifier: Modifier = Modifier,
    emoji: String,
    label: String,
    gradientColors: List<Color>,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(130.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(gradientColors)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(emoji, fontSize = 30.sp)
                Spacer(Modifier.height(8.dp))
                Text(
                    label,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    lineHeight = 17.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// ── Map Placeholder ───────────────────────────────────────────────────────────

@Composable
fun MapPlaceholder() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Fake grid lines
            Column(modifier = Modifier.fillMaxSize()) {
                repeat(5) {
                    HorizontalDivider(
                        color = Color(0xFFCCE5CC),
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(vertical = 18.dp)
                    )
                }
            }

            // Center content
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(EVGreen.copy(alpha = 0.15f), CircleShape)
                        .border(2.dp, EVGreen, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.MyLocation, null, tint = EVGreen, modifier = Modifier.size(28.dp))
                }
                Spacer(Modifier.height(8.dp))
                Text("Map View", color = EVGreenDark, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text(
                    "Google/Apple Maps SDK goes here",
                    color = Color(0xFF4CAF50).copy(alpha = 0.7f),
                    fontSize = 11.sp
                )
            }

            // Mock charger pins
            ChargerPin(Modifier.align(Alignment.TopStart).offset(48.dp, 40.dp))
            ChargerPin(Modifier.align(Alignment.TopEnd).offset((-60).dp, 60.dp))
            ChargerPin(Modifier.align(Alignment.BottomStart).offset(90.dp, (-30).dp))
        }
    }
}

@Composable
fun ChargerPin(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(28.dp)
            .background(
                EVBlue.copy(alpha = 0.9f),
                RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp, bottomEnd = 14.dp)
            )
            .border(
                1.5.dp, Color.White,
                RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp, bottomEnd = 14.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Filled.Bolt, null, tint = Color.White, modifier = Modifier.size(14.dp))
    }
}

// ── Quick Tips ────────────────────────────────────────────────────────────────

@Composable
fun QuickTipsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Lightbulb, null, tint = WarningAmber, modifier = Modifier.size(28.dp))
            Spacer(Modifier.width(12.dp))
            Column {
                Text("💡 Pro Tip", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF795548))
                Text(
                    "Book a Mobile Power Driver 30 minutes in advance during peak hours for fastest response.",
                    fontSize = 12.sp,
                    color = Color(0xFF795548).copy(alpha = 0.8f),
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        title,
        modifier = Modifier.padding(horizontal = 16.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    )
}

// ── Mobile Driver Sheet ───────────────────────────────────────────────────────

@Composable
fun MobileDriverSheet(
    drivers: List<MobileDriver>,
    selectedDriver: MobileDriver?,
    onDriverSelected: (MobileDriver) -> Unit,
    onDismiss: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("🚗 Mobile Power Drivers", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                Text("${drivers.size} drivers available nearby", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = onDismiss) {
                Icon(Icons.Filled.Close, null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        if (selectedDriver != null) {
            DriverConfirmationCard(driver = selectedDriver, onDismiss = onDismiss)
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.height(440.dp)
            ) {
                items(drivers) { driver ->
                    DriverCard(driver = driver, onSelect = { onDriverSelected(driver) })
                }
                item { Spacer(Modifier.height(8.dp)) }
            }
        }
    }
}

@Composable
fun DriverCard(driver: MobileDriver, onSelect: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(EVGreen.copy(alpha = 0.15f), CircleShape)
                    .border(2.dp, EVGreen.copy(alpha = 0.4f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(driver.name.take(1), color = EVGreenDark, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(driver.name, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                Spacer(Modifier.height(4.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InfoChip(Icons.Filled.LocationOn, "${driver.distanceKm} km", EVBlue)
                    InfoChip(Icons.Filled.Schedule, "~${driver.etaMinutes} min", WarningAmber)
                    InfoChip(Icons.Filled.BatteryChargingFull, "${driver.batteryCapacityKwh} kWh", EVGreen)
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, null, tint = WarningAmber, modifier = Modifier.size(14.dp))
                    Text(" ${driver.rating}", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .background(EVGreen.copy(alpha = 0.12f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text("Available", color = EVGreenDark, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun InfoChip(icon: ImageVector, text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = color, modifier = Modifier.size(12.dp))
        Text(" $text", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun DriverConfirmationCard(driver: MobileDriver, onDismiss: () -> Unit) {
    Column(modifier = Modifier.padding(20.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(EVGreen.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                .border(1.dp, EVGreen.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(EVGreen, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.CheckCircle, null, tint = Color.White, modifier = Modifier.size(32.dp))
                }
                Spacer(Modifier.height(12.dp))
                Text("Request Sent!", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = EVGreenDark)
                Text("${driver.name} is on the way", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ConfirmStat("⏱️ ETA", "${driver.etaMinutes} min")
                    ConfirmStat("📍 Distance", "${driver.distanceKm} km")
                    ConfirmStat("⚡ Capacity", "${driver.batteryCapacityKwh} kWh")
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onDismiss,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = EVGreen)
        ) {
            Text("Track Driver", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(
            onClick = onDismiss,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Cancel", fontWeight = FontWeight.Medium, fontSize = 15.sp)
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun ConfirmStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

// ── Fixed Charger Sheet ───────────────────────────────────────────────────────

@Composable
fun FixedChargerSheet(chargers: List<FixedCharger>, onDismiss: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("🔍 Fixed Chargers", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                Text("${chargers.size} stations found", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = onDismiss) {
                Icon(Icons.Filled.Close, null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.height(400.dp)
        ) {
            items(chargers) { charger ->
                ChargerCard(charger)
            }
            item { Spacer(Modifier.height(8.dp)) }
        }
    }
}

@Composable
fun ChargerCard(charger: FixedCharger) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(EVBlue.copy(alpha = 0.12f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.EvStation, null, tint = EVBlue, modifier = Modifier.size(24.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(charger.name, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(charger.address, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Text("${charger.distanceKm} km", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = EVBlue)
            }

            Spacer(Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoBadge("⚡ ${charger.powerKw} kW", EVGreen.copy(alpha = 0.15f), EVGreenDark)
                InfoBadge("🔌 ${charger.availablePorts}/${charger.totalPorts} ports", EVBlue.copy(alpha = 0.12f), EVBlueDark)
                InfoBadge("₫ ${charger.pricePerKwh.toInt()}/kWh", WarningAmber.copy(alpha = 0.15f), Color(0xFF795548))
            }

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(40.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EVBlue)
            ) {
                Text("Navigate", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun InfoBadge(label: String, bgColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(label, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = textColor)
    }
}
