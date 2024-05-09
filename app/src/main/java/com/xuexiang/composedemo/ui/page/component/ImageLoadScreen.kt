package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Size
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.xuexiang.composedemo.R
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * coil:   https://coil-kt.github.io/coil/compose/
 * glide:  https://bumptech.github.io/glide/int/compose.html
 */
@OptIn(ExperimentalCoilApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun ImageLoadScreen() {
    var isClear by remember { mutableStateOf(false) }
    ScrollColumnArea {
        val imageLoader = LocalContext.current.imageLoader
        val glide = Glide.get(LocalContext.current)
        LaunchedEffect(isClear) {
            if (isClear) {
                glide.clearMemory()
                withContext(Dispatchers.IO) {
                    glide.clearDiskCache()
                }
            }
        }
        Button(onClick = {
            imageLoader.diskCache?.clear()
            imageLoader.memoryCache?.clear()
            isClear = true
        }) {
            Text("清除图片缓存")
        }

        //===============coil===================//

        Text(text = "coil-组件使用: AsyncImage")
        AsyncImage(
            model = "https://cdn.pixabay.com/photo/2024/01/12/13/00/field-8503934_1280.jpg",
            placeholder = painterResource(R.drawable.ic_default_img),
            error = painterResource(R.drawable.ic_error_img),
            contentDescription = null,
        )

        Text(text = "coil-Painter使用: AsyncImagePainter")
        Image(
            modifier = Modifier.clip(RoundedCornerShape(12.dp)),
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cdn.pixabay.com/photo/2024/03/07/10/38/simba-8618301_1280.jpg")
                    .size(Size.ORIGINAL).placeholder(R.drawable.ic_default_img)
                    .error(R.drawable.ic_error_img).crossfade(true).build()
            ),
            contentDescription = null
        )

        //===============glide===================//

        Text(text = "glide-组件使用: GlideImage")
        GlideImage(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            model = "https://cdn.pixabay.com/photo/2024/01/12/13/00/field-8503934_1280.jpg",
            loading = placeholder(R.drawable.ic_default_img),
            failure = placeholder(R.drawable.ic_error_img),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}