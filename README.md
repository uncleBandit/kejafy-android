# KejaFy Android Project Architecture Guidelines

This project follows Clean Architecture patterns structured inside a feature-by-package single-module setup under the base package `com.pitahmzalendo.kejafy`.

## Project Directory Blueprint

The root package structure inside `app/src/main/java/com/pitahmzalendo/kejafy/` is organized as follows:

```
com.pitahmzalendo.kejafy
│
├── core/
│   ├── network/
│   ├── database/
│   ├── location/
│   ├── maps/
│   ├── media/
│   ├── auth/
│   └── common/
│
├── feature/
│   │
│   ├── auth/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   │
│   ├── home/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   │
│   ├── listings/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   │
│   ├── property/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   │
│   ├── search/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   │
│   ├── maps/
│   │
│   ├── favorites/
│   │
│   ├── messaging/
│   │
│   ├── profile/
│   │
│   └── notifications/
│
└── navigation/
```

---

## Feature Sub-Package Blueprint

Every feature module inside `feature/` must follow this blueprint structure (exemplified by `feature/listings/`):

```
feature/listings/
│
├── data/
│   ├── remote/
│   │   ├── ListingApi.kt
│   │   └── ListingDto.kt
│   │
│   ├── repository/
│   │   └── ListingRepositoryImpl.kt
│   │
│   └── mapper/
│       └── ListingMapper.kt
│
├── domain/
│   ├── model/
│   │   └── PropertyListing.kt
│   │
│   ├── repository/
│   │   └── ListingRepository.kt
│   │
│   └── usecase/
│       ├── GetListingsUseCase.kt
│       ├── GetListingUseCase.kt
│       └── SearchListingsUseCase.kt
│
└── presentation/
    ├── listing/
    │   ├── ListingScreen.kt
    │   ├── ListingViewModel.kt
    │   └── ListingUiState.kt
    │
    └── details/
        ├── PropertyDetailsScreen.kt
        ├── PropertyDetailsViewModel.kt
        └── PropertyDetailsUiState.kt
```

## Layer Responsibilities

### 1. Domain Layer (Pure Kotlin/Java)
The core business logic layer. It must be independent of all other layers, frameworks, and database implementations.
- **Model:** Represents business objects used across the logic layer.
- **Repository Interface:** Defines contracts for data access that the data layer will implement.
- **Use Cases:** Encapsulates unique, isolated items of business actions or operations.

### 2. Data Layer
Responsible for supplying data to the app, handling network queries, local cache caching, database management, and mapping raw remote layers into pure models.
- **Remote / Local Data Sources:** Implements network interfaces (e.g. Retrofit, Room).
- **DTOs:** Remote network data objects.
- **Mappers:** Translates data objects (DTOs/Entities) into pure domain models.
- **Repository Implementations:** Fulfills repository contracts declared in the domain layer.

### 3. Presentation Layer (UI & States)
Handles the visual layout flow, lifecycle scopes, states, and user events.
- **Screens:** Jetpack Compose layout views.
- **ViewModels:** Manages state changes, flows, and handles interactions with domain use cases.
- **UI States:** Sealed or standard classes representing immutable views of screens at any time point.
