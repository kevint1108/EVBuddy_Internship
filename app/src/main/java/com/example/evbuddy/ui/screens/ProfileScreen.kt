package com.example.evbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evbuddy.ui.theme.EVBlue
import com.example.evbuddy.ui.theme.EVGreen
import com.example.evbuddy.ui.theme.EVGreenDark

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.verticalGradient(listOf(EVGreenDark, EVGreen)))
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White.copy(alpha = 0.25f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("KT", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 28.sp)
                }
                Spacer(Modifier.height(12.dp))
                Text("Kevin Tang", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                Text("EV Buddy Member · Gold Tier ⭐", color = Color.White.copy(alpha = 0.85f), fontSize = 13.sp)
            }
        }

        // EV Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .offset(y = (-8).dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat("🚗", "VinFast VF 8", "My EV")
                VerticalDivider(modifier = Modifier.height(48.dp))
                ProfileStat("⚡", "73 kWh", "Battery Cap.")
                VerticalDivider(modifier = Modifier.height(48.dp))
                ProfileStat("📍", "183 kWh", "Total Charged")
            }
        }

        // Account
        ProfileSection(title = "Account") {
            ProfileMenuItem(Icons.Filled.Person, "Personal Information", EVGreen)
            ProfileMenuItem(Icons.Filled.ElectricCar, "My Vehicles", EVBlue)
            ProfileMenuItem(Icons.Filled.Payment, "Payment Methods", Color(0xFF9C27B0))
            ProfileMenuItem(Icons.Filled.History, "Charging History", Color(0xFFFF5722))
        }

        Spacer(Modifier.height(8.dp))

        // Preferences
        ProfileSection(title = "Preferences") {
            var notifEnabled by remember { mutableStateOf(true) }
            ProfileMenuToggle(Icons.Filled.Notifications, "Push Notifications", EVGreen, notifEnabled) {
                notifEnabled = it
            }
            ProfileMenuItem(Icons.Filled.Language, "Language", EVBlue)
            ProfileMenuItem(Icons.Filled.DarkMode, "Appearance", Color(0xFF607D8B))
        }

        Spacer(Modifier.height(8.dp))

        // About
        ProfileSection(title = "About") {
            ProfileMenuItem(Icons.Filled.Info, "App Version 1.0.0", Color(0xFF607D8B))
            ProfileMenuItem(Icons.Filled.Policy, "Privacy Policy", EVBlue)
            ProfileMenuItem(Icons.Filled.Help, "Help & Support", EVGreen)
        }

        Spacer(Modifier.height(16.dp))

        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFE53935))
        ) {
            Icon(Icons.Filled.Logout, null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Sign Out", fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(20.dp))
    }
}

@Composable
fun ProfileStat(emoji: String, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(emoji, fontSize = 20.sp)
        Spacer(Modifier.height(4.dp))
        Text(value, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        Text(label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun ProfileSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column {
        Text(
            title,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape = RoundedCornerShape(14.dp),
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column { content() }
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, label: String, iconColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(iconColor.copy(alpha = 0.1f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = iconColor, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(12.dp))
        Text(label, modifier = Modifier.weight(1f), fontSize = 14.sp)
        Icon(Icons.Filled.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(18.dp))
    }
    HorizontalDivider(modifier = Modifier.padding(start = 64.dp), thickness = 0.5.dp)
}

@Composable
fun ProfileMenuToggle(icon: ImageVector, label: String, iconColor: Color, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(iconColor.copy(alpha = 0.1f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = iconColor, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(12.dp))
        Text(label, modifier = Modifier.weight(1f), fontSize = 14.sp)
        Switch(
            checked = checked,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(checkedThumbColor = EVGreen)
        )
    }
    HorizontalDivider(modifier = Modifier.padding(start = 64.dp), thickness = 0.5.dp)
}
