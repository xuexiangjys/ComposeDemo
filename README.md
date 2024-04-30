# ComposeDemo

Compose的使用演示, Compose官方文档: https://developer.android.com/develop/ui/compose/documentation?hl=zh-cn

## 关于我

| 公众号   | 掘金     |  知乎    |  CSDN   |   简书   |   思否  |   哔哩哔哩  |   今日头条
|---------|---------|--------- |---------|---------|---------|---------|---------|
| [我的Android开源之旅](https://t.1yb.co/Irse)  |  [点我](https://juejin.im/user/598feef55188257d592e56ed/posts)    |   [点我](https://www.zhihu.com/people/xuexiangjys/posts)       |   [点我](https://xuexiangjys.blog.csdn.net/)  |   [点我](https://www.jianshu.com/u/6bf605575337)  |   [点我](https://segmentfault.com/u/xuexiangjys)  |   [点我](https://space.bilibili.com/483850585)  |   [点我](https://img.rruu.net/image/5ff34ff7b02dd)


## 组件使用

### 网络图片加载: coil-compose

[coil-compose](https://coil-kt.github.io/coil/compose/)

* 组件使用: AsyncImage

```kotlin
AsyncImage(
    model = "https://cdn.pixabay.com/photo/2024/01/12/13/00/field-8503934_1280.jpg",
    placeholder = painterResource(R.drawable.ic_default_img),
    error = painterResource(R.drawable.ic_error_img),
    contentDescription = null,
)
```

* Painter使用: AsyncImagePainter

```kotlin
Image(
    painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://cdn.pixabay.com/photo/2024/03/07/10/38/simba-8618301_1280.jpg")
            .size(Size.ORIGINAL).placeholder(R.drawable.ic_default_img)
            .error(R.drawable.ic_error_img).crossfade(true).build()
    ), contentDescription = null
)
```