package com.example.internapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.internapp.R

@Composable
fun NewsScreen(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = null,
                Modifier.size(35.dp)
            )

            Spacer(modifier = Modifier.width(100.dp))

            Text(
                text = "News",
                fontSize = 35.sp,
                color = Color.Black
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(5.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(30.dp)
            ) {
                Text("Daily", fontSize = 25.sp, color = Color.White)

                Spacer(modifier = Modifier.width(20.dp))

                Text("Health", fontSize = 25.sp, color = Color.White)

                Spacer(modifier = Modifier.width(20.dp))

                Text("Sport", fontSize = 25.sp, color = Color.White)

                Spacer(modifier = Modifier.width(20.dp))

                Text("Health", fontSize = 25.sp, color = Color.White)
            }
        }
    }
}