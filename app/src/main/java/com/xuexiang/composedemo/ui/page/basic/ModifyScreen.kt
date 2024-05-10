package com.xuexiang.composedemo.ui.page.basic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.xuexiang.composedemo.ui.widget.ScrollColumnArea
import com.xuexiang.composedemo.ui.widget.showToast

/**
 * 修饰符列表: https://developer.android.com/develop/ui/compose/modifiers-list?hl=zh-cn
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ModifyScreen() {
    val context = LocalContext.current

    ScrollColumnArea {
        //=====padding======//
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .border(2.dp, Color.Blue)
        ) {
            Text(
                modifier = Modifier
                    // 这里的padding类似margin
                    .padding(24.dp)
                    .background(Color.LightGray), text = "Modifier.padding(24.dp)"
            )
        }

        //=====clickable======//
        Box(
            modifier = Modifier
                .clickable { showToast(context, "点击事件!") }
                .border(2.dp, Color.Blue),
        ) {
            Text(
                modifier = Modifier.padding(24.dp), text = "Modifier.clickable{ }"
            )
        }

        //=====combinedClickable======//
        Box(
            modifier = Modifier
                .combinedClickable(onLongClick = {
                    showToast(context, "长按事件!")
                }, onDoubleClick = {
                    showToast(context, "双击事件!")
                }, onClick = {
                    showToast(context, "点击事件!")
                })
                .border(2.dp, Color.Blue),
        ) {
            Text(
                modifier = Modifier.padding(24.dp), text = "Modifier.combinedClickable{ }"
            )
        }

        //=====size======//
        Box(
            modifier = Modifier
                .size(120.dp)
                .border(2.dp, Color.Blue),
        ) {
            Text(modifier = Modifier.padding(12.dp), text = "Modifier.size(120.dp)")
        }

        //=====requiredSize======//
        Box(
            modifier = Modifier
                .size(80.dp)
                .border(2.dp, Color.Blue),
        ) {
            // requiredXXX 就是子view的尺寸不受父view的约束
            Text(
                modifier = Modifier
                    .requiredSize(100.dp)
                    .background(Color.LightGray),
                text = "Modifier.requiredSize(100.dp)"
            )
        }


        //=====offset======//
        Box(
            modifier = Modifier.border(2.dp, Color.Blue),
        ) {
            // padding 和 offset 之间的区别在于，向可组合项添加 offset 不会改变其测量值
            Text(
                modifier = Modifier
                    .offset(x = 30.dp)
                    .background(Color.LightGray),
                text = "Modifier.offset(x = 30.dp)"
            )
        }

        //=====weight======//

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .height(50.dp)
                    .background(Color.Blue)
            ) {
                Text(text = "Modifier.weight(2f)")
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .background(Color.Red)
            ) {
                Text(text = "Modifier.weight(1f)")
            }
        }

        //=====blur======//

        Box(contentAlignment = Alignment.Center) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier.blur(
                    radius = 8.dp, edgeTreatment = BlurredEdgeTreatment(
                        RoundedCornerShape(8.dp)
                    )
                ),
                model = "https://cdn.pixabay.com/photo/2024/01/12/13/00/field-8503934_1280.jpg",
                contentDescription = null,
            )
            Text(text = "Modifier.blur(8.dp)", color = Color.White)
        }

        //=====aspectRatio======//

        Box(contentAlignment = Alignment.Center) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .circleShape(),
                model = "https://cdn.pixabay.com/photo/2024/01/12/13/00/field-8503934_1280.jpg",
                contentDescription = null,
            )
            Text(text = "Modifier.aspectRatio(1f)\n             .clip(CircleShape)", color = Color.White)
        }
    }
}


fun Modifier.circleShape() = aspectRatio(1f).clip(CircleShape)

@Composable
fun Modifier.circle(): Modifier {
    return this then Modifier.aspectRatio(1f).clip(CircleShape)
}