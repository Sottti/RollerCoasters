package com.sottti.roller.coasters.presentation.about.me.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.cuvva.roller.coasters.presentation.design.system.text.Text
import com.sottti.roller.coasters.presentation.about.me.data.externalNavigationPrimaryColor
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction
import com.sottti.roller.coasters.presentation.about.me.model.AboutMeAction.OpenUrl
import com.sottti.roller.coasters.presentation.about.me.model.TopicDescription
import com.sottti.roller.coasters.presentation.about.me.model.TopicHyperlink
import com.sottti.roller.coasters.presentation.design.system.colors.color.colors
import com.sottti.roller.coasters.presentation.design.system.dimensions.dimensions
import com.sottti.roller.coasters.presentation.format.annotatedLinkString

@Composable
internal fun BottomSheetContent(
    onAction: (AboutMeAction) -> Unit,
    topicDescription: TopicDescription?,
) {
    topicDescription?.let {
        Column(
            modifier = Modifier.padding(dimensions.padding.medium),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        ) {
            Text.Title.Large(textResId = topicDescription.titleResId)
            Spacer(modifier = Modifier.padding(dimensions.padding.medium))
            Text.Body.Medium(
                textResId = topicDescription.bodyResId,
                modifier = Modifier.fillMaxWidth()
            )
            topicDescription.hyperlink?.let {
                Spacer(modifier = Modifier.padding(dimensions.padding.medium))
                Hyperlink(topicDescription.hyperlink, onAction)
            }
        }
    }
}

@Composable
private fun Hyperlink(
    hyperlink: TopicHyperlink,
    onAction: (AboutMeAction) -> Unit,
) {
    val annotatedLinkString = annotatedLinkString(
        color = colors.primary,
        text = stringResource(hyperlink.textResId),
        url = stringResource(hyperlink.urlResId),
    )

    val primaryColor = externalNavigationPrimaryColor(hyperlink.urlResId)
    ClickableText(
        modifier = Modifier.fillMaxWidth(),
        text = annotatedLinkString,
        style = MaterialTheme.typography.bodyMedium,
        onClick = { onAction(OpenUrl(hyperlink.urlResId, primaryColor = primaryColor)) }
    )
}