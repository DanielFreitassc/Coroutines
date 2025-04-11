package com.danielfreitassc.contatosapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.danielfreitassc.contatosapp.User

@Composable
fun ContactApp(users: List<User>, onNewUser: (newUser: User) -> Unit) {

    Scaffold (
        floatingActionButton = {
            // New contact button
            FloatingActionButton (
                onClick = { onNewUser(fakeUsers[(0..7).random()])},
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Lista de Contatos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

            // Contact list
            LazyColumn {
                items(users) { user ->
                    ContactItem(user)
                }
            }
        }
    }
}


@Composable
fun ContactItem(user: User) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = user.userName,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
        )
        Text(
            text = user.userPhone,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
        )
    }
}

val fakeUsers = mutableListOf(
    User(0, "Thyerri Mezzari", "(48) 99915-9783"),
    User(0, "Android ", "(12) 3456-7890"),
    User(0, "Kotlin ", "(12) 3456-7890"),
    User(0, "Compose ", "(12) 3456-7890"),
    User(0, "Column", "(12) 3456-7890"),
    User(0, "Row ", "(12) 3456-7890"),
    User(0, "Modifier ", "(12) 3456-7890"),
    User(0, "fillMaxSize ", "(12) 3456-7890"),
)