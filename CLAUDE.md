# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build the project
./gradlew build

# Run unit tests
./gradlew test

# Run a single test class
./gradlew test --tests "com.example.utrechtverkennen.ExampleUnitTest"

# Run Android instrumented tests (requires emulator/device)
./gradlew connectedAndroidTest

# Clean build
./gradlew clean build

# Install debug APK on connected device
./gradlew installDebug
```

## Architecture

This is a Jetpack Compose Android app showcasing locations in Utrecht. It uses an adaptive layout that responds to window size.

### Key Patterns

**MVVM with StateFlow**: `CityViewModel` manages UI state via `CityUiState` data class, exposed as `StateFlow`. The view model filters locations by category and tracks selected items.

**Adaptive Navigation**: The app uses three layout types based on `WindowWidthSizeClass`:
- `Compact` → Separate screens with NavHost navigation
- `Medium` → Modal navigation drawer with list/detail side-by-side
- `Expanded` → Permanent navigation drawer with list/detail side-by-side

This logic is in `MyCityApp.kt:determineLayout()` and `AdaptiveNavigationDrawer()`.

**Static Data Layer**: `DataSource` is a singleton object containing hardcoded location data with Dutch descriptions (HTML formatted). No backend/database.

### Package Structure

- `model/` - Data classes: `Location` and `LocationCategory` enum
- `data/` - `DataSource` singleton with static location data and category images
- `ui/` - Composables, ViewModel, and layout type enum
- `ui/theme/` - Material3 theming (colors, typography)

### Dependencies

Uses Jetpack Compose with Material3, Navigation Compose, and `material3-window-size-class` for adaptive layouts. Version catalog in `gradle/libs.versions.toml`.
