package com.xuexiang.composedemo.ui.page.component

import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Size
import com.xuexiang.composedemo.R
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea

/**
 * https://coil-kt.github.io/coil/compose/
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageLoadScreen() {
    ScrollColumnArea {
        val imageLoader = LocalContext.current.imageLoader
        Button(onClick = {
            imageLoader.diskCache?.clear()
            imageLoader.memoryCache?.clear()
        }) {
            Text("清除图片缓存")
        }

        Text(text = "组件使用: AsyncImage")
        AsyncImage(
            model = "https://cdn.pixabay.com/photo/2024/01/12/13/00/field-8503934_1280.jpg",
            placeholder = painterResource(R.drawable.ic_default_img),
            error = painterResource(R.drawable.ic_error_img),
            contentDescription = null,
        )

        Text(text = "Painter使用: AsyncImagePainter")
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cdn.pixabay.com/photo/2024/03/07/10/38/simba-8618301_1280.jpg")
                    .size(Size.ORIGINAL).placeholder(R.drawable.ic_default_img)
                    .error(R.drawable.ic_error_img).crossfade(true).build()
            ), contentDescription = null
        )
    }
}