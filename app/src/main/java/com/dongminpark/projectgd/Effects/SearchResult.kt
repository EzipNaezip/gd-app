package com.dongminpark.projectgd.Effects


import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dongminpark.projectgd.Button.SaveButton
import com.dongminpark.projectgd.Model.DalleImages
import com.dongminpark.projectgd.R
import com.dongminpark.projectgd.ui.theme.suite
import com.dongminpark.projectgd.Utils.List.TAGLIST
import com.google.gson.JsonObject

@Composable
fun SearchResult(jsonObject: JsonObject) {
    val tags = arrayListOf<String>()
    val itemId: Int?
    var chatLogSerialNumber: Int? = 0

    val results = jsonObject.getAsJsonArray("response")

    if (results[0].isJsonNull || results[0].asJsonObject.get("itemId").isJsonNull) {
        itemId = null
    } else {
        itemId = results[0].asJsonObject.get("itemId").asInt
        chatLogSerialNumber = results[0].asJsonObject.get("chatLogSerialNumber").asInt
    }


    // 이미지 추출
    val images: ArrayList<DalleImages> = arrayListOf()
    results.forEach { resultItem ->
        val resultItemObject = resultItem.asJsonObject
        var itemId2: Int? = null

        if (!resultItem.isJsonNull && !resultItem.asJsonObject.get("itemId").isJsonNull)
            itemId2 = resultItemObject.get("itemId").asInt

        val url = resultItemObject.get("url").asString


        val imagesItem = DalleImages(
            itemId = itemId2,
            url = url
        )

        images.add(imagesItem)
    }

    // 디스크립션 추출
    val description = jsonObject.get("description").asString

    if (itemId != null) {
        // 태그 추출
        val tag = jsonObject.getAsJsonArray("tag")
        val tags: ArrayList<String> = arrayListOf()

        tag.forEach {
            tags.add(it.toString())
        }
    }

    // 하나면 가운데로 가게
    LazyVerticalGrid(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(images) { photo ->
            ImageScreen(photo.url ?: "")
        }
    }

    if (results.size() > 1) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd,
        ) {
            SaveButton(chatLogSerialNumber, onClick = {
                // 동글뱅이 호출(진행중이라는 표시)
            })
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        MaterialTheme.colors.secondaryVariant,
                        RoundedCornerShape(12.dp)
                    )
            )
            {
                Box(modifier = Modifier.padding(15.dp)) {
                    PostContent(description)
                }

            }
        }
        item {
            TagList(tags = tags)
        }
    }

}


@Composable
fun ImageScreen(url: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { isExpanded = true },
    ) {
        if (isExpanded) {
            Dialog(onDismissRequest = { isExpanded = false }) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight()
                        .padding(vertical = 16.dp)
                        .clickable { isExpanded = false },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    images(url = url)
                }
            }
        }

        images(url = url)
    }
}

@Composable
fun images(url: String) {
    val painter = // 이미지 로드 중 및 실패 시 표시할 이미지 리소스를 설정할 수 있습니다.
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    // 이미지 로드 중 및 실패 시 표시할 이미지 리소스를 설정할 수 있습니다.
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.placeholder)
                }).build()
        )
    Image(
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .clip(RoundedCornerShape(0.dp))
            .aspectRatio(1f),
        painter = painter,
        contentDescription = "Image"

    )
}


@Composable
fun TagList(tags: ArrayList<String>) {
    Row() {
        for (i in tags.indices) {
            Row {
                Text(
                    text = "#${TAGLIST[tags[i].toInt()]}",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp),
                    color = Color.Black,
                    fontFamily = suite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        }
    }
}