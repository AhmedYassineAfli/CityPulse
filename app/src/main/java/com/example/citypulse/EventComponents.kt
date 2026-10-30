package com.example.citypulse

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 3.1 CategoryBadge (Atelier 6)
 * Affiche la catégorie avec fond contrasté conforme WCAG.
 */
@Composable
fun CategoryBadge(category: String, modifier: Modifier = Modifier) {
    Text(
        text = category,
        color = Color.White,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(categoryColor(category))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

/**
 * 3.2 & Étape 4 FavoriteButton animé et accessible (Atelier 6)
 * - Posé sur un disque blanc pour rester visible sur toutes photos
 * - Zone tactile garantie 48x48 dp
 * - contentDescription contextualisée (« Ajouter $eventTitle aux favoris »)
 * - Animations fluides de couleur (tween) et d'échelle avec ressort (spring)
 */
@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    eventTitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tint by animateColorAsState(
        targetValue = if (isFavorite) FavoriteActive else FavoriteInactive,
        animationSpec = tween(durationMillis = 300),
        label = "favoriteTint"
    )

    val scale by animateFloatAsState(
        targetValue = if (isFavorite) 1.2f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "favoriteScale"
    )

    Surface(
        shape = CircleShape,
        color = Color.White.copy(alpha = 0.92f),
        modifier = modifier
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = if (isFavorite)
                    "Retirer $eventTitle des favoris"
                else
                    "Ajouter $eventTitle aux favoris",
                tint = tint,
                modifier = Modifier.scale(scale)
            )
        }
    }
}

/**
 * Étape 5 Puces de filtre accessibles et animées (Atelier 6)
 * - Double encodage visuel (coche animée + changement de couleur)
 * - Déclaré à TalkBack avec .selectable(role = Role.Tab)
 * - Zone tactile minimale 48dp
 */
@Composable
fun CategoryChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background by animateColorAsState(
        targetValue = if (selected) BlueDark else Color.White,
        animationSpec = tween(200),
        label = "chipBackground"
    )
    val content by animateColorAsState(
        targetValue = if (selected) Color.White else TextOnLight,
        animationSpec = tween(200),
        label = "chipContent"
    )
    val shape = RoundedCornerShape(50)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .clip(shape)
            .background(background)
            .border(1.dp, if (selected) BlueDark else FavoriteInactive, shape)
            .selectable(selected = selected, onClick = onClick, role = Role.Tab)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        AnimatedVisibility(visible = selected) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null, // l'état est déjà annoncé par selectable
                tint = content,
                modifier = Modifier.padding(end = 6.dp)
            )
        }
        Text(
            text = label,
            color = content,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

/**
 * Étape 6 Bannière « À la une » avec LiveRegion (Atelier 6)
 */
@Composable
fun FeaturedBanner(
    event: Event,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = BannerBackground,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .semantics { liveRegion = LiveRegionMode.Polite }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = "À la une : ${event.title}",
                color = BannerText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp)
            )
            IconButton(onClick = onDismiss) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Fermer la bannière À la une",
                    tint = BannerText
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryChipPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        CategoryChip("Concert", selected = true, onClick = {})
        CategoryChip("Expo", selected = false, onClick = {})
    }
}

@Preview(showBackground = true, fontScale = 2f)
@Composable
private fun CategoryChipLargeTextPreview() {
    CategoryChipPreview()
}
