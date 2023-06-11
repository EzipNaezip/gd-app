package com.dongminpark.projectgd.Screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.dongminpark.projectgd.App
import com.dongminpark.projectgd.Effects.ImageFormat
import com.dongminpark.projectgd.Effects.PostContent
import com.dongminpark.projectgd.Effects.SearchBar
import com.dongminpark.projectgd.Effects.SearchResult
import com.dongminpark.projectgd.Model.Example
import com.dongminpark.projectgd.Retrofit.RetrofitManager
import com.dongminpark.projectgd.ui.theme.suite
import com.dongminpark.projectgd.Effects.SearchBarShimmer
import com.dongminpark.projectgd.Utils.Constants.TAG
import com.dongminpark.projectgd.Utils.MESSAGE
import com.dongminpark.projectgd.Utils.RESPONSE_STATE
import com.dongminpark.projectgd.Utils.USER
import com.google.gson.JsonParser
import java.util.*

val examples = arrayListOf<Example>()
val examplesShow = arrayListOf<Example>()

@Composable
fun MainScreen() {
    var loading by rememberSaveable{ mutableStateOf(true) }
    var isOkay by rememberSaveable{ mutableStateOf(false) }
    var state by rememberSaveable { mutableStateOf(0) } // 검색 상태 state
    var scrollState = rememberLazyGridState()
    var imagesJson by rememberSaveable { mutableStateOf("") }

    if (loading) {
        loading = false
        examples.clear()
        RetrofitManager.instance.example(completion = { responseState, resposeBody ->
            when (responseState) {
                RESPONSE_STATE.OKAY -> {
                    examples.addAll(resposeBody!!)

                    // 랜덤하게 20개의 요소를 추출하여 targetList에 추가
                    val random = Random(System.currentTimeMillis())
                    while (examplesShow.size < 20 && examples.isNotEmpty()) {
                        val randomIndex = random.nextInt(examples.size) // 랜덤한 인덱스 선택
                        val randomElement = examples[randomIndex] // 랜덤한 요소 선택
                        examplesShow.add(randomElement) // 추출된 요소를 targetList에 추가
                        examples.removeAt(randomIndex) // 추출한 요소를 원본 리스트에서 삭제
                    }

                    isOkay = true
                    Log.d(TAG, "MainScreen: examples load success")
                }
                RESPONSE_STATE.FAIL -> {
                    Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "MainScreen: examples load Error")
                }
            }
        })
    }

    if (isOkay) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            when (state) {
                0 -> { // 검색 전
                    MainSpacer(scrollState = scrollState)
                    TextMessage(state)
                    SearchBar( // 검색창
                        onSearch = {
                            state = 1

                            // 검색 api
                            RetrofitManager.instance.gptImages(
                                it,
                                completion = { responseState, respnseBody ->

                                    when (responseState) {
                                        RESPONSE_STATE.OKAY -> {
                                            if (respnseBody != null) {
                                                //images = respnseBody?: JsonObject()
                                                imagesJson = respnseBody.toString()
                                            }
                                            Log.d(TAG, "api 호출 성공 : $respnseBody")
                                            state = 2
                                        }
                                        RESPONSE_STATE.FAIL -> {
                                            Toast.makeText(
                                                App.instance,
                                                MESSAGE.ERROR,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            Log.d(TAG, "api 호출 에러 : $respnseBody")
                                            state = 0
                                        }
                                    }
                                })
                        }
                    )
                    MainSpacer(scrollState = scrollState)

                    ExampleItems(scrollState)
                }
                1 -> { // 검색 중
                    imagesJson = ""
                    MainSpacer(scrollState = scrollState)
                    TextMessage(state)
                    SearchBarShimmer(onClick = {
                        // stop generate api 호출
                        RetrofitManager.instance.gptStop(completion = { responseState ->
                            when (responseState) {
                                RESPONSE_STATE.OKAY -> {
                                    Log.d(TAG, "api 호출 성공")
                                    state = 0
                                }
                                RESPONSE_STATE.FAIL -> {
                                    Toast.makeText(App.instance, MESSAGE.ERROR, Toast.LENGTH_SHORT)
                                        .show()
                                    Log.d(TAG, "api 호출 에러")
                                }
                            }
                        })
                    })
                    MainSpacer(scrollState = scrollState)

                    ExampleItems(scrollState)
                }
                2 -> { // 검색 후
                    TextMessage(state)
                    SearchBar( // 검색창
                        onSearch = {
                            state = 1

                            // 검색 api
                            RetrofitManager.instance.gptImages(
                                it,
                                completion = { responseState, respnseBody ->

                                    when (responseState) {
                                        RESPONSE_STATE.OKAY -> {
                                            if (respnseBody != null) {
                                                //images = respnseBody?: JsonObject()
                                                imagesJson = respnseBody.toString()
                                            }
                                            Log.d(TAG, "api 호출 성공 : $respnseBody")
                                            state = 2
                                        }
                                        RESPONSE_STATE.FAIL -> {
                                            Toast.makeText(
                                                App.instance,
                                                MESSAGE.ERROR,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            Log.d(TAG, "api 호출 에러 : $respnseBody")
                                            state = 0
                                        }
                                    }
                                })
                        }
                    )
                    SearchResult(JsonParser().parse(imagesJson).asJsonObject)
                    MainSpacer(scrollState = scrollState)
                }
            }
        }
    }
}

@Composable
fun MainSpacer(scrollState: LazyGridState) {
    var scrollOffset = scrollState.firstVisibleItemScrollOffset.dp / 6
    if (scrollState.firstVisibleItemIndex == 0) {
        if (80.dp > scrollOffset)
            Spacer(modifier = Modifier.padding(80.dp - scrollOffset))
    }
}

@Composable
fun ExampleItems(scrollState: LazyGridState) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var showNum by rememberSaveable { mutableStateOf(0) }

    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .padding(top = 8.dp),
        columns = GridCells.Fixed(2),
        state = scrollState
    ) {
        items(examplesShow.size) {
            ModalFrame(examplesShow.get(it), onClick = {
                showNum = it
                showDialog = true
            })
        }
    }

    if (showDialog) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PopUpModal(examplesShow.get(showNum), showDialog, onDismiss = { showDialog = false })
        }
    }
}

@Composable
fun TextMessage(num: Int) {
    val textList: List<String> =
        listOf("새로운 아이디어를 찾아보세요!", "디자이너가 열심히 그리는중...", "원하는 인테리어가 없으신가요? 말씀해주세요!")
    Text(
        text = textList[num],
        fontFamily = suite,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .padding(20.dp)
            .padding(top = 5.dp)
    )
}

@Composable
fun ModalFrame(example: Example, onClick: () -> Unit) {
    // 매개변수로 해당 사진을 받아야함.
    // 사진을 띄우고 클릭하면 모달을 띄움
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(0.dp, Color.Transparent)
            .clickable { onClick() }
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageFormat(url = example.imgUrl)
    }
}

@Composable
fun PopUpModal(
    example: Example,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    // 매개변수로 받은 객체의 이미지와 텍스트 출력
    if (showDialog) {
        Dialog(
            onDismissRequest = { onDismiss() }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Button(
                    onClick = { onDismiss() },
                    contentPadding = PaddingValues(
                        start = 0.dp,
                        end = 0.dp,
                        top = 0.dp,
                        bottom = 12.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ImageFormat(example.imgUrl) // 이미지

                        Spacer(modifier = Modifier.padding(vertical = 20.dp))

                        PostContent(example.prompt) // 설명

                        Spacer(modifier = Modifier.padding(vertical = 20.dp))

                        OutlinedButton(
                            onClick = {
                                      // 복사 기능
                                copyToClipboard(App.instance, example.prompt)
                                      },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primaryVariant
                            ),
                            modifier = Modifier.fillMaxWidth(0.8f),
                            shape = RoundedCornerShape(30)
                        ) {
                            Text(
                                text = "Copy",
                                color =  Color.White,
                                fontFamily = suite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

fun copyToClipboard(context: Context, text: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text", text)
    clipboardManager.setPrimaryClip(clipData)
}