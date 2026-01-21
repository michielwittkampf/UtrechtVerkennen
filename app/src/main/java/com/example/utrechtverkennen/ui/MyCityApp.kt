package com.example.utrechtverkennen.ui

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Expanded
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Medium
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.utrechtverkennen.R
import com.example.utrechtverkennen.data.DataSource
import com.example.utrechtverkennen.model.Location
import com.example.utrechtverkennen.model.LocationCategory
import com.example.utrechtverkennen.ui.LayoutType.ALL_IN_ONE
import com.example.utrechtverkennen.ui.LayoutType.MODAL_FOR_LOCATION_CATEGORIES
import com.example.utrechtverkennen.ui.LayoutType.ALL_SEPARATE_SCREENS
import com.example.utrechtverkennen.ui.theme.Typography
import com.example.utrechtverkennen.ui.theme.UtrechtverkennenTheme

@Composable
fun MyCityApp(
    windowWidthSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onBackPressed: () -> Unit = {},
) {
    val images: Map<LocationCategory, Int> = DataSource.locationCategoryImages

    val viewModel: CityViewModel = viewModel()
    val uiState: CityUiState = viewModel.uiState.collectAsState().value

    val layout = determineLayout(windowWidthSize)

    AdaptiveNavigationDrawer(
        layout = layout,
        selectedLocationCategory = uiState.selectedLocationCategory,
        onSelectLocationCategory = { viewModel.setLocationCategory(it) },
    ) {
        if (layout in setOf(ALL_IN_ONE, MODAL_FOR_LOCATION_CATEGORIES)) {
            Row(modifier) {
                LocationListPanel(
                    locations = uiState.filteredLocations,
                    modifier = Modifier
                        .width(400.dp),
                    selectedLocation = uiState.selectedLocation,
                    onSelectLocation = { viewModel.setLocation(it) },
                )
                LocationDetailsPanel(
                    location = uiState.selectedLocation,
                    imageId = images[uiState.selectedLocation?.type],
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        } else {
            NavHost(
                navController = navController,
                startDestination = "locations-list"
            ) {
                composable("locations-list") {
                    LocationListPanel(
                        locations = uiState.filteredLocations,
                        selectedLocation = uiState.selectedLocation,
                        onSelectLocation = {
                            viewModel.setLocation(it)
                            navController.navigate("location-details")
                        },
                    )
                }
                composable("location-details") {
                    LocationDetailsPanel(
                        location = uiState.selectedLocation,
                        imageId = DataSource.locationCategoryImages[uiState.selectedLocationCategory]
                    )
                }
            }
        }
    }
}

fun determineLayout(windowWidthSize: WindowWidthSizeClass): LayoutType {
    return when (windowWidthSize) {
        Compact -> ALL_SEPARATE_SCREENS
        Medium -> MODAL_FOR_LOCATION_CATEGORIES
        Expanded -> ALL_IN_ONE
        else -> ALL_SEPARATE_SCREENS
    }
}

@Composable
fun AdaptiveNavigationDrawer(
    layout: LayoutType,
    selectedLocationCategory: LocationCategory?,
    onSelectLocationCategory: (LocationCategory) -> Unit,
    content: @Composable () -> Unit,
) {
    val drawerWidth = 280.dp
    val drawerContainerColor = MaterialTheme.colorScheme.surfaceVariant

    when (layout) {
        ALL_IN_ONE -> {
            PermanentNavigationDrawer({
                PermanentDrawerSheet(
                    modifier = Modifier.width(drawerWidth),
                    drawerContainerColor = drawerContainerColor,
                ) {
                    LocationCategoriesListPanel(
                        selectedLocationCategory = selectedLocationCategory,
                        onSelectLocationCategory = onSelectLocationCategory,
                    )
                }
            }
            ) { content() }
        }

        else -> {
            ModalNavigationDrawer({
                ModalDrawerSheet(
                    modifier = Modifier.width(drawerWidth),
                    drawerContainerColor = drawerContainerColor,
                ) {
                    LocationCategoriesListPanel(
                        selectedLocationCategory = selectedLocationCategory,
                        onSelectLocationCategory = onSelectLocationCategory,
                    )
                }
            }
            ) { content() }
        }
    }
}

@Composable
fun LocationCategoriesListPanel(
    selectedLocationCategory: LocationCategory?,
    onSelectLocationCategory: (LocationCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 30.dp, vertical = 30.dp)
    ) {
        Header(
            text = stringResource(R.string.app_title),
        )
        LocationCategoriesList(selectedLocationCategory, onSelectLocationCategory)
    }
}

@Composable
private fun LocationCategoriesList(
    selectedLocationCategory: LocationCategory?,
    onSelectLocationCategory: (LocationCategory) -> Unit
) {
    LocationCategory.entries.forEach { type ->
        NavigationDrawerItem(
            label = { Text(type.title) },
            selected = (type == selectedLocationCategory),
            onClick = { onSelectLocationCategory(type) },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationListPanel(
    locations: List<Location>,
    selectedLocation: Location?,
    onSelectLocation: (Location) -> Unit,
    modifier: Modifier = Modifier,
    withTopBar: Boolean = true,
    title: String = "",
    onBackButtonClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 30.dp)
    ) {
        if (withTopBar) {
            AppTopBar(title, onBackButtonClick)
        } else Header()
        LocationList(locations, onSelectLocation)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppTopBar(title: String, onBackButtonClick: () -> Unit) {
    TopAppBar(
        title = { PanelTitle(title) },
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        }
    )
}

@Composable
private fun LocationList(
    locations: List<Location>,
    onSelectLocation: (Location) -> Unit
) {
    locations.forEach {
        LocationMenuItem(
            location = it,
            onSelectLocation = onSelectLocation,
        )
    }
}

@Composable
private fun LocationDetailsPanel(
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
            LocationDetails(imageId, location)
        }
    }
}

@Composable
private fun LocationDetails(imageId: Int?, location: Location) {
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

@Composable
private fun Header(text: String? = null) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        if (text != null) {
            PanelTitle(text)
        }
    }
}

@Composable
private fun PanelTitle(text: String) {
    Text(
        text = text,
        style = Typography.displaySmall
    )
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
    UtrechtverkennenTheme {
        BoxWithConstraints {
            val windowSize = WindowSizeClass.calculateFromSize(DpSize(maxWidth, maxHeight))
            MyCityApp(
                windowWidthSize = windowSize.widthSizeClass,
                onBackPressed = {},
            )
        }
    }
}

