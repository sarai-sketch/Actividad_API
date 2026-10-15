package com.cyberqhatu.attackontitan.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cyberqhatu.attackontitan.model.Character
import com.cyberqhatu.attackontitan.model.Episode
import com.cyberqhatu.attackontitan.model.Titan
import com.cyberqhatu.attackontitan.ui.theme.AttackOnTitanTheme
import com.cyberqhatu.attackontitan.ui.theme.CardSurface
import com.cyberqhatu.attackontitan.ui.theme.DarkBackground
import com.cyberqhatu.attackontitan.ui.theme.DarkRed
import com.cyberqhatu.attackontitan.ui.theme.GoldAmber
import com.cyberqhatu.attackontitan.ui.theme.TextPrimary
import com.cyberqhatu.attackontitan.ui.theme.TextSecondary
import com.cyberqhatu.attackontitan.viewmodel.AotTab
import com.cyberqhatu.attackontitan.viewmodel.MainUiState
import com.cyberqhatu.attackontitan.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    val charactersState by viewModel.charactersState.collectAsStateWithLifecycle()
    val titansState by viewModel.titansState.collectAsStateWithLifecycle()
    val episodesState by viewModel.episodesState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(DarkRed),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = GoldAmber,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "ATTACK ON TITAN",
                                color = GoldAmber,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif
                            )
                            Text(
                                text = "Base de Datos Oficial",
                                color = TextSecondary,
                                fontSize = 11.sp
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF181818)
                )
            )
        },
        bottomBar = {
            AotBottomNavigation(
                selectedTab = selectedTab,
                onTabSelected = { viewModel.selectTab(it) }
            )
        },
        containerColor = DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                placeholder = { Text("Buscar en la base de datos...", color = TextSecondary, fontSize = 14.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GoldAmber,
                    unfocusedBorderColor = Color(0xFF333333),
                    focusedContainerColor = CardSurface,
                    unfocusedContainerColor = CardSurface,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                singleLine = true
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                when (selectedTab) {
                    AotTab.CHARACTERS -> {
                        RenderState(
                            state = charactersState,
                            onRetry = { viewModel.loadCharacters() }
                        ) { list ->
                            val filtered = list.filter {
                                it.name.contains(searchQuery, ignoreCase = true) ||
                                        (it.status?.contains(searchQuery, ignoreCase = true) == true)
                            }
                            CharactersSection(characters = filtered)
                        }
                    }

                    AotTab.TITANS -> {
                        RenderState(
                            state = titansState,
                            onRetry = { viewModel.loadTitans() }
                        ) { list ->
                            val filtered = list.filter {
                                it.name.contains(searchQuery, ignoreCase = true) ||
                                        (it.currentInheritor?.contains(searchQuery, ignoreCase = true) == true)
                            }
                            TitansSection(titans = filtered)
                        }
                    }

                    AotTab.EPISODES -> {
                        RenderState(
                            state = episodesState,
                            onRetry = { viewModel.loadEpisodes() }
                        ) { list ->
                            val filtered = list.filter {
                                it.name.contains(searchQuery, ignoreCase = true) ||
                                        it.episode.contains(searchQuery, ignoreCase = true)
                            }
                            EpisodesSection(episodes = filtered)
                        }
                    }

                    AotTab.ABOUT -> AboutSection()
                }
            }
        }
    }
}

@Composable
fun <T> RenderState(
    state: MainUiState<T>,
    onRetry: () -> Unit,
    content: @Composable (List<T>) -> Unit
) {
    when (state) {
        is MainUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = DarkRed)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Conectando con la API...", color = TextSecondary, fontSize = 13.sp)
                }
            }
        }

        is MainUiState.Success -> {
            if (state.data.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No se encontraron resultados", color = TextSecondary)
                }
            } else {
                content(state.data)
            }
        }

        is MainUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Error al conectar con la API",
                        color = DarkRed,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = state.message,
                        color = TextSecondary,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onRetry,
                        colors = ButtonDefaults.buttonColors(containerColor = DarkRed)
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Reintentar", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun AotBottomNavigation(
    selectedTab: AotTab,
    onTabSelected: (AotTab) -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFF181818),
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = selectedTab == AotTab.CHARACTERS,
            onClick = { onTabSelected(AotTab.CHARACTERS) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Personajes") },
            label = { Text("Personajes") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkRed,
                selectedTextColor = GoldAmber,
                indicatorColor = Color(0xFF2C2C2C),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = selectedTab == AotTab.TITANS,
            onClick = { onTabSelected(AotTab.TITANS) },
            icon = { Icon(Icons.Default.Star, contentDescription = "Titanes") },
            label = { Text("Titanes") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkRed,
                selectedTextColor = GoldAmber,
                indicatorColor = Color(0xFF2C2C2C),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = selectedTab == AotTab.EPISODES,
            onClick = { onTabSelected(AotTab.EPISODES) },
            icon = { Icon(Icons.Default.PlayArrow, contentDescription = "Episodios") },
            label = { Text("Episodios") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkRed,
                selectedTextColor = GoldAmber,
                indicatorColor = Color(0xFF2C2C2C),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = selectedTab == AotTab.ABOUT,
            onClick = { onTabSelected(AotTab.ABOUT) },
            icon = { Icon(Icons.Default.Info, contentDescription = "Info") },
            label = { Text("Exploración") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkRed,
                selectedTextColor = GoldAmber,
                indicatorColor = Color(0xFF2C2C2C),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}

@Composable
fun CharactersSection(characters: List<Character>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(characters) { character ->
            Card(
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFF333333)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    character.img?.let { imgUrl ->
                        AsyncImage(
                            model = imgUrl,
                            contentDescription = character.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Black)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = character.name,
                                color = GoldAmber,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )

                            character.status?.let { status ->
                                Surface(
                                    color = if (status.contains("Vivo", ignoreCase = true) || status.contains("Viva", ignoreCase = true)) Color(0xFF2E7D32) else DarkRed,
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Text(
                                        text = status,
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }

                        character.occupation?.let { occupation ->
                            Text(
                                text = "Ocupación: $occupation",
                                color = TextPrimary,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        Text(
                            text = "Altura: ${character.height ?: "Desconocida"} • Género: ${character.gender ?: "N/A"}",
                            color = TextSecondary,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )

                        character.species?.let { speciesList ->
                            if (speciesList.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    speciesList.take(2).forEach { sp ->
                                        AssistChip(
                                            onClick = {},
                                            label = { Text(sp, fontSize = 10.sp, color = TextPrimary) },
                                            colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFF2C2C2C))
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TitansSection(titans: List<Titan>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(titans) { titan ->
            Card(
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFF8B0000)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    titan.img?.let { imgUrl ->
                        AsyncImage(
                            model = imgUrl,
                            contentDescription = titan.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Black)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = titan.name,
                            color = DarkRed,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Altura: ${titan.height ?: "N/A"} • Lealtad: ${titan.allegiance ?: "Desconocida"}",
                            color = GoldAmber,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(top = 2.dp)
                        )

                        titan.currentInheritor?.let { inheritor ->
                            Text(
                                text = "Heredero Actual: $inheritor",
                                color = TextPrimary,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        titan.abilities?.let { abilities ->
                            if (abilities.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    abilities.take(2).forEach { ability ->
                                        AssistChip(
                                            onClick = {},
                                            label = { Text(ability, fontSize = 10.sp, color = TextPrimary) },
                                            colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFF2B1A1A))
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodesSection(episodes: List<Episode>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(episodes) { episode ->
            Card(
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFF333333)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    episode.img?.let { imgUrl ->
                        AsyncImage(
                            model = imgUrl,
                            contentDescription = episode.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Black)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = episode.name,
                                color = TextPrimary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )

                            Surface(
                                color = GoldAmber,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = episode.episode,
                                    color = Color.Black,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        episode.characters?.let { characters ->
                            if (characters.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    characters.take(3).forEach { charName ->
                                        AssistChip(
                                            onClick = {},
                                            label = { Text(charName, fontSize = 10.sp, color = TextPrimary) },
                                            colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFF2C2C2C))
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AboutSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = CardSurface),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.5.dp, GoldAmber),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(DarkRed)
                        .border(2.dp, GoldAmber, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = GoldAmber,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "CUERPO DE EXPLORACIÓN",
                    color = GoldAmber,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Serif
                )

                Text(
                    text = "「心臓を捧げよ! • Shinzou wo Sasageyo!」",
                    color = DarkRed,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Conectado exitosamente con la API oficial de Attack on Titan (https://api.attackontitanapi.com/). Consulta la información en tiempo real de Personajes, Titanes y Episodios.",
                    color = TextSecondary,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val sampleViewModel = remember { MainViewModel() }
    AttackOnTitanTheme {
        HomeScreen(viewModel = sampleViewModel)
    }
}
