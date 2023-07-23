package com.fitri.harrypottercharacters.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.fitri.harrypottercharacters.di.Injection
import com.fitri.harrypottercharacters.ui.ViewModelFactory
import com.fitri.harrypottercharacters.ui.common.UiState
import com.fitri.harrypottercharacters.model.Character

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllCharacters()
            }
            is UiState.Success -> {
                HomeContent(
                    characterList = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,)
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    characterList: List<Character>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    Column {
        TopAppBar (
            title = {
                Text(text = "Harry Potter Characters",
                    Modifier.padding(horizontal = 8.dp))

            },
        )
        LazyColumn {
            items(characterList) {data ->
                CharacterListItem(
                    name = data.name,
                    photoUrl = data.photoUrl,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.id)
                    })
            }
        }
    }
}


@Composable
fun CharacterListItem(
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}
