package com.pitahmzalendo.kejafy.feature.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(
    state: OnboardingUiState,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onIntentSelect: (UserIntent) -> Unit,
    onAreaChange: (String) -> Unit,
    onTypeChange: (String) -> Unit,
    onPriceRangeChange: (String, String) -> Unit,
    onNotificationsToggle: (Boolean) -> Unit,
    onGuestContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state.currentStep) {
                OnboardingStep.WELCOME -> {
                    Text(
                        text = "Welcome to KejaFy",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 40.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Your unified smart real estate hub to discover, negotiate, and secure perfect properties flawlessly.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                OnboardingStep.INTENT_SELECTION -> {
                    Text(
                        text = "What are you looking for?",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        UserIntent.values().forEach { intent ->
                            val isSelected = state.selectedIntent == intent
                            Button(
                                onClick = { onIntentSelect(intent) },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary 
                                    else MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary 
                                    else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                Text(text = intent.name)
                            }
                        }
                    }
                }

                OnboardingStep.PREFERRED_AREA -> {
                    Text(
                        text = "Preferred Area",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = state.preferredArea,
                        onValueChange = onAreaChange,
                        label = { Text("Enter City or Neighborhood") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                OnboardingStep.PROPERTY_TYPE -> {
                    Text(
                        text = "Property Type",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = state.propertyType,
                        onValueChange = onTypeChange,
                        label = { Text("e.g. Apartment, Villa, Office") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                OnboardingStep.PRICE_RANGE -> {
                    Text(
                        text = "Price Range",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedTextField(
                            value = state.minPrice,
                            onValueChange = { onPriceRangeChange(it, state.maxPrice) },
                            label = { Text("Min Price") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = state.maxPrice,
                            onValueChange = { onPriceRangeChange(state.minPrice, it) },
                            label = { Text("Max Price") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                OnboardingStep.NOTIFICATIONS -> {
                    Text(
                        text = "Stay Updated?",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Enable push notifications for matching listings")
                        Switch(
                            checked = state.notificationsEnabled,
                            onCheckedChange = onNotificationsToggle
                        )
                    }
                }

                OnboardingStep.ACCOUNT_CREATION -> {
                    Text(
                        text = "Ready to Begin",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = onNextClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Create Account / Google Sign-In")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = onGuestContinueClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Continue as Guest")
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (state.currentStep != OnboardingStep.WELCOME) {
                OutlinedButton(onClick = onBackClick) {
                    Text("Back")
                }
            } else {
                Spacer(modifier = Modifier.width(1.dp))
            }

            if (state.currentStep != OnboardingStep.ACCOUNT_CREATION) {
                Button(onClick = onNextClick) {
                    Text("Next")
                }
            }
        }
    }
}
