package com.pitahmzalendo.kejafy.feature.listings.presentation.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pitahmzalendo.kejafy.feature.listings.domain.model.EnvironmentScore
import com.pitahmzalendo.kejafy.feature.listings.domain.model.FactorScore

@Composable
fun EnvironmentSection(
    score: EnvironmentScore,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Environment",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Overall Score", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "${score.overall ?: "N/A"} / 100",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        score.noise?.let { FactorBar("Noise Level", it) }
        score.floodRisk?.let { FactorBar("Flood Risk", it) }
        score.security?.let { FactorBar("Security", it) }
        score.utilities?.let { FactorBar("Utilities Reliability", it) }
    }
}

@Composable
private fun FactorBar(label: String, factor: FactorScore) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
            Text(text = "${factor.score * 20}/100", style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { factor.score / 5f },
            modifier = Modifier.fillMaxWidth(),
            color = if (factor.score > 3) MaterialTheme.colorScheme.primary else Color.Gray
        )
    }
}
