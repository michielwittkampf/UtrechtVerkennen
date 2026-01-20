package com.example.utrechtontdekker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.utrechtontdekker.R
import com.example.utrechtontdekker.data.DataSource
import com.example.utrechtontdekker.model.Location
import com.example.utrechtontdekker.model.LocationType
import com.example.utrechtontdekker.ui.LayoutType.COMBINE_CATEGORIES_PLACES_AND_DETAILS
import com.example.utrechtontdekker.ui.theme.Typography
import com.example.utrechtontdekker.ui.theme.UtrechtOntdekkerTheme

@Composable
fun MyCityApp(
    modifier: Modifier = Modifier,
    windowWidthSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit
) {
    val images: Map<LocationType, Int> = DataSource.locationTypeImages

    val viewModel: CityViewModel = viewModel()
    val uiState: CityUiState = viewModel.uiState.collectAsState().value

    val layout = determineLayout(windowWidthSize)

    if (layout == COMBINE_CATEGORIES_PLACES_AND_DETAILS) {
        AdaptiveNavigationDrawer(
            layout = layout,
            selectedLocationType = uiState.selectedLocationType,
            onSelectLocationType = { viewModel.setLocationType(it) },
        ) {
            Row(modifier) {
                PlacesList(
                    locations = uiState.filteredLocations,
                    modifier = Modifier
                        .width(400.dp),
                    selectedLocation = uiState.selectedLocation,
                    onSelectLocation = { viewModel.setLocation(it) },
                )
                PlaceDetails(
                    location = uiState.selectedLocation,
                    imageId = images[uiState.selectedLocation?.type],
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

fun determineLayout(windowWidthSize: WindowWidthSizeClass): LayoutType {
    return COMBINE_CATEGORIES_PLACES_AND_DETAILS
}

@Composable
fun AdaptiveNavigationDrawer(
    layout: LayoutType,
    selectedLocationType: LocationType?,
    onSelectLocationType: (LocationType) -> Unit,
    content: @Composable () -> Unit,
) {
    val drawerWidth = 280.dp
    val drawerContainerColor = MaterialTheme.colorScheme.surfaceVariant

    when (layout) {
        COMBINE_CATEGORIES_PLACES_AND_DETAILS -> {
            PermanentNavigationDrawer({
                PermanentDrawerSheet(
                    modifier = Modifier.width(drawerWidth),
                    drawerContainerColor = drawerContainerColor,
                ) { CategoriesDrawerContent(
                    selectedLocationType = selectedLocationType,
                    onSelectLocationType = onSelectLocationType,
                ) }
            }
            ) { content() }
        }

        else -> {
            ModalNavigationDrawer({
                ModalDrawerSheet(
                    modifier = Modifier.width(drawerWidth),
                    drawerContainerColor = drawerContainerColor,
                ) { CategoriesDrawerContent(
                    selectedLocationType = selectedLocationType,
                    onSelectLocationType = onSelectLocationType,
                ) }
            }
            ) { content() }
        }
    }
}

@Composable
fun CategoriesDrawerContent(
    modifier: Modifier = Modifier,
    selectedLocationType: LocationType?,
    onSelectLocationType: (LocationType) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 30.dp, vertical = 30.dp)
    ) {
        Header(
            text = stringResource(R.string.app_title),
        )
        LocationType.entries.forEach { type ->
            NavigationDrawerItem(
                label = { Text(type.title) },
                selected = (type == selectedLocationType),
                onClick = { onSelectLocationType(type) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@Composable
fun PlacesList(
    locations: List<Location>,
    modifier: Modifier = Modifier,
    selectedLocation: Location? = null,
    onSelectLocation: (Location) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 30.dp)
    ) {
        Header()
        locations.forEach {
            LocationMenuItem(
                location = it,
                onSelectLocation = onSelectLocation,
            )
        }
    }
}

@Composable
fun PlaceDetails(
    location: Location?,
    imageId: Int?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 30.dp, vertical = 30.dp)
    ) {
        if (location != null) {
            Header(
                text = location.displayName,
            )
            if (imageId != null) {
                Image(
                    painter = painterResource(imageId),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )
            }
            Text(
                text = AnnotatedString.fromHtml(location.descriptionHtml),
                style = Typography.bodyLarge,
            )
        }
    }
}

@Composable
fun Header(text: String? = null) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        if (text != null) {
            Text(
                text = text,
                style = Typography.displaySmall
            )
        }
    }
}

@Composable
fun LocationMenuItem(
    location: Location,
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(vertical = 20.dp),
    onSelectLocation: (Location) -> Unit = {},
) {
    Text(
        text = location.displayName,
        style = Typography.titleLarge,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onSelectLocation(location) })
            .padding(padding)
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(device = "id:pixel_tablet", showBackground = true)
@Preview(device = "id:medium_tablet", showBackground = true)
@Preview(device = "id:pixel_5", showBackground = true)
@Composable
fun MyCityAppPreview() {
    UtrechtOntdekkerTheme {
        BoxWithConstraints {
            val windowSize = WindowSizeClass.calculateFromSize(DpSize(maxWidth, maxHeight))
            MyCityApp(
                windowWidthSize = windowSize.widthSizeClass,
                onBackPressed = {},
            )
        }
    }
}

