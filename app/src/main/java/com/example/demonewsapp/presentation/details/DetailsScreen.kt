package com.example.demonewsapp.presentation.details

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.demonewsapp.domain.model.Article
import com.example.demonewsapp.domain.model.Source
import com.example.demonewsapp.presentation.Dimens.ArticleImageHeight
import com.example.demonewsapp.presentation.Dimens.MediumPadding1
import com.example.demonewsapp.presentation.details.components.DetailsEvent
import com.example.demonewsapp.presentation.details.components.DetailsTopBar
import com.example.demonewsapp.ui.theme.PreviewTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = false
)
@Composable
fun DetailsScreenPreview() {
    PreviewTheme {
        DetailsScreen(
            article = Article(
                author = "Author",
                content = "Some content",
                description = "Very important description",
                publishedAt = "Today 12:00",
                source = Source(id = "1", name = "BBC"),
                title = "Tittle. We have some interesting and very important news",
                url = "http://url.com",
                urlToImage = ""
            ),
            event = {}
        ) {

        }
    }
}
