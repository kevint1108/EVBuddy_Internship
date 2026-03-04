package com.example.evbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evbuddy.ui.theme.EVBlue
import com.example.evbuddy.ui.theme.EVGreen
import com.example.evbuddy.ui.theme.EVGreenDark
import com.example.evbuddy.ui.theme.WarningAmber

data class ChargingRequest(
    val id: String,
    val type: String,
    val date: String,
    val status: RequestStatus,
    val energyKwh: Double,
    val cost: Int
)

enum class RequestStatus { COMPLETED, IN_PROGRESS, CANCELLED }

private val mockRequests = listOf(
    ChargingRequest("REQ-001", "Mobile Power Driver", "Feb 28, 2026 · 09:15", RequestStatus.COMPLETED, 25.0, 87500),
    ChargingRequest("REQ-002", "Fixed Charger", "Feb 26, 2026 · 14:30", RequestStatus.COMPLETED, 40.0, 128000),
    ChargingRequest("REQ-003", "Mobile Power Driver", "Feb 24, 2026 · 11:00", RequestStatus.CANCELLED, 0.0, 0),
    ChargingRequest("REQ-004", "Fixed Charger", "Feb 21, 2026 · 19:45", RequestStatus.COMPLETED, 18.5, 59200)
)

@Composable
fun MyRequestsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(EVGreen)
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column {
                Text("My Requests", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
                Text("Your charging history", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatMiniCard(Modifier.weight(1f), "Total Sessions", "12", EVGreen)
                    StatMiniCard(Modifier.weight(1f), "Energy Used", "183 kWh", EVBlue)
                    StatMiniCard(Modifier.weight(1f), "Total Spent", "₫585K", WarningAmber)
                }
                Spacer(Modifier.height(8.dp))
                Text("Recent Activity", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.height(4.dp))
            }
            items(mockRequests) { request ->
                RequestCard(request)
            }
        }
    }
}

@Composable
fun StatMiniCard(modifier: Modifier, label: String, value: String, color: Color) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(value, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = color)
            Text(label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 14.sp)
        }
    }
}

@Composable
fun RequestCard(request: ChargingRequest) {
    val (statusColor, statusLabel) = when (request.status) {
        RequestStatus.COMPLETED -> Pair(EVGreen, "Completed")
        RequestStatus.IN_PROGRESS -> Pair(WarningAmber, "In Progress")
        RequestStatus.CANCELLED -> Pair(Color(0xFFE53935), "Cancelled")
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(statusColor.copy(alpha = 0.12f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    if (request.type.contains("Mobile")) Icons.Filled.DirectionsCar else Icons.Filled.EvStation,
                    null,
                    tint = statusColor,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(request.type, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Text(request.date, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                if (request.status != RequestStatus.CANCELLED) {
                    Spacer(Modifier.height(3.dp))
                    Text("${request.energyKwh} kWh · ₫${request.cost}", fontSize = 11.sp, color = EVGreenDark, fontWeight = FontWeight.Medium)
                }
            }
            Box(
                modifier = Modifier
                    .background(statusColor.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(statusLabel, color = statusColor, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
