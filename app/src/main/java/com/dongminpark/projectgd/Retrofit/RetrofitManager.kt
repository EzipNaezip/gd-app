package com.dongminpark.projectgd.Retrofit

import android.content.Context
import android.net.Uri
import android.util.Log
import com.dongminpark.projectgd.Model.*
import com.dongminpark.projectgd.Screens.OAuthData
import com.dongminpark.projectgd.Utils.API
import com.dongminpark.projectgd.Utils.Constants.TAG
import com.dongminpark.projectgd.Utils.RESPONSE_STATE
import com.dongminpark.projectgd.Utils.USER
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RetrofitManager {
    companion object {
        val instance = RetrofitManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    // Bookmark
    fun bookmarksCreatePostNum(postNum: Int, completion: (RESPONSE_STATE) -> Unit){
        val call = iRetrofit?.bookmarksCreatePostnum(postNum) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStop - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }

    fun bookmarksDeletePostNum(postNum: Int, completion: (RESPONSE_STATE) -> Unit){
        val call = iRetrofit?.bookmarksDeletePostnum(postNum) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStop - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }




    // firebase와 DB 연결
    fun firebaseConnect(
        uid: String?,
        completion: (RESPONSE_STATE) -> Unit
    ) { // 통신 성공 여부 , 토큰, 멤버 여부
        val term = uid ?: ""

        val call = iRetrofit?.firebaseConnect(uid = term) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "FirebaseConnect - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "FirebaseConnect - onResponse() called / respose : ${response.body()}")
                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val accessToken = data.get("jwt").asString
                            val user = data.get("user").asJsonObject
                            val userId = user.get("userId").asInt

                            // 일단은 이런식으로 사용. 나중에는 security적용해서
                            OAuthData.ACCESS_TOKEN = accessToken
                            USER.ACCESS_TOKEN = accessToken
                            USER.USERID = userId

                            // 이런방식으로 하면 안되고
                            Log.e(TAG, "onResponse: ${OAuthData.ACCESS_TOKEN}")
                            completion(RESPONSE_STATE.OKAY)
                        }
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }
        })
    }

    // comment-controller
    // 댓글 작성
    fun comments(content: String, postNum: Int, completion: (RESPONSE_STATE) -> Unit) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("content", content)
        //jsonObject.addProperty("writerId", writerId)
        jsonObject.addProperty("postNum", postNum)

        val call = iRetrofit?.comments(CommentRequestDto = jsonObject) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "gptImages - onFailure() called / t: $t")
                var errorMessage: String = t.message.toString()
                t.printStackTrace();
                Log.e(TAG, "onFailure: $errorMessage")
                completion(RESPONSE_STATE.FAIL)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptImages - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            completion(RESPONSE_STATE.OKAY)
                        }
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }
        })
    }

    // 댓글 삭제
    fun commentsSerialNumDelete(serialNum: Int, completion: (RESPONSE_STATE) -> Unit) {
        val call = iRetrofit?.commentsSerialNumDelete(serialNum) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStop - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }

    // 댓글 수정
    fun commentsSerialNumUpdate(
        serialNum: Int,
        intput: String,
        completion: (RESPONSE_STATE) -> Unit
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("sentence", intput)

        val call = iRetrofit?.commentsSerialNumUpdate(serialNum = serialNum, InputDto = jsonObject)
            ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "gptImages - onFailure() called / t: $t")
                var errorMessage: String = t.message.toString()
                t.printStackTrace();
                Log.e(TAG, "onFailure: $errorMessage")
                completion(RESPONSE_STATE.FAIL)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptImages - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            completion(RESPONSE_STATE.OKAY)
                        }
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }
        })
    }

    // 댓글 리스팅
    fun commentsListPostNum(
        postNum: Int?,
        start: Int = 0,
        display: Int = 0,
        completion: (RESPONSE_STATE, ArrayList<Comments>?) -> Unit
    ) {
        val term = postNum ?: 0
        val call = iRetrofit?.commentsListPostNum(postNum = term, start, display) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStore - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            val comments = arrayListOf<Comments>()
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val commentList = data.getAsJsonArray("commentList")

                            commentList.forEach {
                                val body = it.asJsonObject
                                val user = body.get("user").asJsonObject
                                val userId = user.get("userId").asInt
                                val name = user.get("name").asString
                                val profileImgUrl = user.get("profileImgUrl").asString
                                val serialNum = body.get("serialNum").asInt
                                val content = body.get("content").asString
                                val isMe = body.get("isMe").asBoolean

                                val comment = Comments(
                                    serialNum = serialNum,
                                    userId = userId,
                                    name = name,
                                    profileImgUrl = profileImgUrl,
                                    content = content,
                                    isMe = isMe
                                )
                                comments.add(comment)
                            }

                            completion(RESPONSE_STATE.OKAY, comments)
                        }
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL, null)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, null)
            }

        })
    }

    // Example
    fun example(completion: (RESPONSE_STATE, ArrayList<Example>?) -> Unit){
        val call = iRetrofit?.example() ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStore - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            val examples = arrayListOf<Example>()
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val commentList = data.getAsJsonArray("example")

                            commentList.forEach {
                                val body = it.asJsonObject
                                val imgUrl = body.get("imgUrl").asString
                                val prompt = body.get("prompt").asString

                                val example = Example(
                                    imgUrl = API.BASE_URL + imgUrl,
                                    prompt = prompt
                                )
                                Log.e(TAG, "onResponse: ${example.prompt}", )
                                examples.add(example)
                            }

                            completion(RESPONSE_STATE.OKAY, examples)
                        }
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL, null)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, null)
            }

        })
    }



    // follow-controller


    // chat-gpt-controller
    // 메인 검색창 검색
    fun gptImages(
        sentence: String?,
        completion: (RESPONSE_STATE, JsonObject?) -> Unit
    ) { // 통신 성공 여부 , 토큰, 멤버 여부
        val jsonObject = JsonObject()
        jsonObject.addProperty("sentence", sentence)

        val call = iRetrofit?.gptImages(sentence = jsonObject) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "gptImages - onFailure() called / t: $t")
                var errorMessage: String = t.message.toString()
                t.printStackTrace();
                Log.e(TAG, "onFailure: $errorMessage")
                completion(RESPONSE_STATE.FAIL, null)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptImages - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            completion(RESPONSE_STATE.OKAY, data)
                        }
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL, null)
                    }
                }
            }
        })
    }

    // 메인 검색 중지
    fun gptStop(completion: (RESPONSE_STATE) -> Unit) {
        val call = iRetrofit?.gptStop() ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStop - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }

    // 메인 결과 저장
    fun gptStore(serialNum: Int?, completion: (RESPONSE_STATE) -> Unit) {
        val term = serialNum ?: 0
        val call = iRetrofit?.gptStore(serialNum = term) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStore - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }

    // Follow
    // 팔로워 목록
    fun followFollowersUserId(userId: Int, completion: (RESPONSE_STATE, ArrayList<FollowUser>?) -> Unit){
        val call = iRetrofit?.followFollowersUserId(userId = userId) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStore - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            val followers = arrayListOf<FollowUser>()
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val follower = data.getAsJsonArray("follower")

                            follower.forEach {
                                val person = it.asJsonObject
                                val userId = person.get("userId").asInt
                                val name = person.get("name").asString
                                val isFollow = person.get("follow").asBoolean
                                val profileImgUrl = person.get("profileImgUrl").asString

                                val user = FollowUser(
                                    userId = userId,
                                    name = name,
                                    profileImgUrl = profileImgUrl,
                                    follow = isFollow
                                )

                                followers.add(user)
                            }
                            completion(RESPONSE_STATE.OKAY,followers)
                        }
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL, null)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, null)
            }

        })
    }

    // 팔로우
    fun followFollowingFollowingId(followingId: Int, completion: (RESPONSE_STATE) -> Unit){
        val call = iRetrofit?.followFollowingFollowingId(followingId = followingId) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStore - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }

    // 팔로잉 목록
    fun followFollowingsUserId(userId: Int, completion: (RESPONSE_STATE, ArrayList<FollowUser>?) -> Unit){
        val call = iRetrofit?.followFollowingsUserId(userId = userId) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStore - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            val followings = arrayListOf<FollowUser>()
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val following = data.getAsJsonArray("following")?:JsonArray()

                            following.forEach {
                                val person = it.asJsonObject
                                val userId = person.get("userId").asInt
                                val name = person.get("name").asString
                                val isFollow = person.get("follow").asBoolean
                                val profileImgUrl = person.get("profileImgUrl").asString

                                val user = FollowUser(
                                    userId = userId,
                                    name = name,
                                    follow = isFollow,
                                    profileImgUrl = profileImgUrl
                                )

                                followings.add(user)
                            }
                            completion(RESPONSE_STATE.OKAY,followings)
                        }
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL, null)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, null)
            }

        })
    }

    // 언팔로우
    fun followUnfollowingFollowerId(followerId: Int, completion: (RESPONSE_STATE) -> Unit){
        val call = iRetrofit?.followUnfollowingFollowerId(followerId = followerId) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStore - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }

    // Likes
    fun likesCreatePostNum(postNum: Int, completion: (RESPONSE_STATE) -> Unit){
        val call = iRetrofit?.likesCreatePostnum(postNum) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "likes Create - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }

    fun likesDeletePostNum(postNum: Int, completion: (RESPONSE_STATE) -> Unit){
        val call = iRetrofit?.likesDeletePostnum(postNum) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "likes Delete - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }

    // post-controller
    // 포스트 삭제
    fun postDelete(postNum: Int?, completion: (RESPONSE_STATE) -> Unit) {
        val term = postNum ?: 0
        val call = iRetrofit?.postDelete(term) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "postDelete - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL)
            }

        })
    }

    // 포스트 검색
    fun postFilter(
        tagName: String,
        start: Int = 0,
        display: Int = 0,
        completion: (RESPONSE_STATE, ArrayList<Post>?) -> Unit
    ) {
        val call =
            iRetrofit?.postFilter(tagName = tagName, start = start, display = display) ?: return//

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, null)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            var parsedPostDataArray = ArrayList<Post>()
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val results = data.getAsJsonArray("postList")

                            results.forEach { resultItem ->
                                val resultItemObject = resultItem.asJsonObject

                                val postNum = resultItemObject.get("postNum").asInt
                                val thumbnailImgUrl: String?

                                if (resultItemObject.get("thumbnailImgUrl").isJsonNull) {
                                    thumbnailImgUrl = null
                                } else thumbnailImgUrl =
                                    resultItemObject.get("thumbnailImgUrl").asString

                                val bookmark: Boolean
                                if (resultItemObject.get("bookmark").isJsonNull) {
                                    bookmark = false
                                } else bookmark = resultItemObject.get("bookmark").asBoolean

                                val me: Boolean
                                if (resultItemObject.get("me").isJsonNull) {
                                    me = false
                                } else me = resultItemObject.get("bookmark").asBoolean

                                val postItem = Post(
                                    postNum = postNum,
                                    thumbnailImgUrl = API.BASE_URL + thumbnailImgUrl,
                                    bookmark = bookmark,
                                    me = me
                                )

                                Log.e(TAG, "onResponse: $postItem")

                                parsedPostDataArray.add(postItem)
                            }

                            completion(RESPONSE_STATE.OKAY, parsedPostDataArray)
                        }
                    }
                    else -> completion(RESPONSE_STATE.FAIL, null)
                }
            }
        })
    }

    // 포스트 리스트 조회
    fun postList(
        start: Int = 0,
        display: Int = 0,
        completion: (RESPONSE_STATE, ArrayList<Post>?) -> Unit
    ) {
        val call = iRetrofit?.postList(start = start, display = display) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, null)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            var parsedPostDataArray = ArrayList<Post>()
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val results = data.getAsJsonArray("postList")

                            results.forEach { resultItem ->
                                val resultItemObject = resultItem.asJsonObject

                                val postNum = resultItemObject.get("postNum").asInt
                                val thumbnailImgUrl: String?

                                if (resultItemObject.get("thumbnailImgUrl").isJsonNull) {
                                    thumbnailImgUrl = null
                                } else thumbnailImgUrl =
                                    resultItemObject.get("thumbnailImgUrl").asString

                                val bookmark = resultItemObject.get("bookmark").asBoolean

                                val me: Boolean
                                if (resultItemObject.get("me").isJsonNull) {
                                    me = false
                                } else me = resultItemObject.get("bookmark").asBoolean

                                val postItem = Post(
                                    postNum = postNum,
                                    thumbnailImgUrl = API.BASE_URL + thumbnailImgUrl,
                                    bookmark = bookmark,
                                    me = me
                                )

                                Log.e(TAG, "onResponse: $postItem")

                                parsedPostDataArray.add(postItem)
                            }

                            completion(RESPONSE_STATE.OKAY, parsedPostDataArray)
                        }
                    }
                    else -> completion(RESPONSE_STATE.FAIL, null)
                }
            }
        })
    }

    fun postListPostnum(postNum: Int?, completion: (RESPONSE_STATE, PostPostnum?) -> Unit) {
        val term = postNum ?: 0
        val call = iRetrofit?.postListDetail(postNum = term) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptStore - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            val paths = arrayListOf<String>()
                            val tags = arrayListOf<String>()

                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val post = data.get("post").asJsonObject
                            val timestamp = post.get("timestamp").asString
                            val writerId = post.get("writerId").asJsonObject
                            val userId = writerId.get("userId").asInt
                            val name = writerId.get("name").asString
                            val profileImgUrl = writerId.get("profileImgUrl").asString
                            val likesCount = post.get("likesCount")?.asInt ?: 0
                            val path = post.get("path")?.asString ?: ""
                            val bookmark = post.get("bookmark")?.asBoolean ?: false
                            val like = post.get("like")?.asBoolean ?: false
                            val follow = post.get("follow")?.asBoolean ?: false
                            val isMe = post.get("isMe")?.asBoolean ?: false
                            val tagIds = post.getAsJsonArray("tagIds") ?: JsonArray()
                            val description = post.get("description")?.asString ?: ""

                            val list = path.split("|")
                            Log.e(TAG, "onResponse: $path")

                            list.forEach {
                                paths.add(API.BASE_URL + it)
                            }


                            tagIds.forEach {
                                tags.add(it.asString)
                            }

                            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                            val formatter = SimpleDateFormat("yyyy년 MM월 dd일")
                            val outputDateString = formatter.format(parser.parse(timestamp))
                            Log.e(TAG, "onResponse: $outputDateString")

                            var postPostnum = PostPostnum(
                                postNum = term,
                                timestamp = outputDateString,
                                userId = userId,
                                name = name,
                                profileImgUrl = profileImgUrl,
                                likesCount = likesCount,
                                path = paths,
                                bookmark = bookmark,
                                like = like,
                                follow = follow,
                                isMe = isMe,
                                tagIds = tags,
                                description = description
                            )

                            Log.e(TAG, "입력된 값 확인: $postPostnum")

                            completion(RESPONSE_STATE.OKAY, postPostnum)
                        }
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL, null)
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, null)
            }

        })
    }

    fun postPopular(
        start: Int = 0,
        display: Int = 0,
        completion: (RESPONSE_STATE, ArrayList<Post>?) -> Unit
    ) {
        val call = iRetrofit?.postPopular(start = start, display = display) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, null)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            var parsedPostDataArray = ArrayList<Post>()
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val results = data.getAsJsonArray("postList")

                            results.forEach { resultItem ->
                                val resultItemObject = resultItem.asJsonObject

                                val postNum = resultItemObject.get("postNum").asInt
                                val thumbnailImgUrl: String?

                                if (resultItemObject.get("thumbnailImgUrl").isJsonNull) {
                                    thumbnailImgUrl = null
                                } else thumbnailImgUrl =
                                    resultItemObject.get("thumbnailImgUrl").asString

                                val bookmark = resultItemObject.get("bookmark").asBoolean

                                val me: Boolean
                                if (resultItemObject.get("me").isJsonNull) {
                                    me = false
                                } else me = resultItemObject.get("bookmark").asBoolean

                                val postItem = Post(
                                    postNum = postNum,
                                    thumbnailImgUrl = API.BASE_URL + thumbnailImgUrl,
                                    bookmark = bookmark,
                                    me = me
                                )

                                Log.e(TAG, "onResponse: $postItem")

                                parsedPostDataArray.add(postItem)
                            }

                            completion(RESPONSE_STATE.OKAY, parsedPostDataArray)
                        }
                    }
                    else -> completion(RESPONSE_STATE.FAIL, null)
                }
            }
        })
    }

    fun postSearch(
        start: Int = 0,
        display: Int = 0,
        keyword: String,
        completion: (RESPONSE_STATE, ArrayList<Post>?) -> Unit
    ) {
        val call =
            iRetrofit?.postSearch(start = start, display = display, keyword = keyword) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, null)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            var parsedPostDataArray = ArrayList<Post>()
                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val results = data.getAsJsonArray("postList")

                            results.forEach { resultItem ->
                                val resultItemObject = resultItem.asJsonObject

                                val postNum = resultItemObject.get("postNum").asInt
                                val thumbnailImgUrl: String?

                                if (resultItemObject.get("thumbnailImgUrl").isJsonNull) {
                                    thumbnailImgUrl = null
                                } else thumbnailImgUrl =
                                    resultItemObject.get("thumbnailImgUrl").asString

                                val bookmark = resultItemObject.get("bookmark").asBoolean

                                val me: Boolean
                                if (resultItemObject.get("me").isJsonNull) {
                                    me = false
                                } else me = resultItemObject.get("bookmark").asBoolean

                                val postItem = Post(
                                    postNum = postNum,
                                    thumbnailImgUrl = API.BASE_URL + thumbnailImgUrl,
                                    bookmark = bookmark,
                                    me = me
                                )

                                Log.e(TAG, "onResponse: $postItem")

                                parsedPostDataArray.add(postItem)
                            }

                            completion(RESPONSE_STATE.OKAY, parsedPostDataArray)
                        }
                    }
                    else -> completion(RESPONSE_STATE.FAIL, null)
                }
            }
        })
    }

    // upload
    fun upload(file: Uri, context: Context, completion: (RESPONSE_STATE) -> Unit){
//        val fi = File(file.path)
//        Log.e(TAG, "upload: $fi", )
//        val requestFile: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), fi)
//        Log.e(TAG, "upload: ${requestFile}", )
//        val body: MultipartBody.Part = MultipartBody.Part.createFormData("file", fi.name, requestFile)
        context.contentResolver.openInputStream(file)?.use { inputStream ->
            val bytes = inputStream.readBytes()
            val requestFile: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), bytes)
            val body: MultipartBody.Part = MultipartBody.Part.createFormData("file", File(file.path).name, requestFile)

            // 나머지 코드는 원래대로
            val call = iRetrofit?.upload(body) ?: return

            Log.e(TAG, "upload: $call", )

            call.enqueue(object : retrofit2.Callback<JsonElement> {
                // 응답 실패시
                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    Log.d(TAG, "gptImages - onFailure() called / t: $t")
                    var errorMessage: String = t.message.toString()
                    t.printStackTrace();
                    Log.e(TAG, "onFailure: $errorMessage")
                    completion(RESPONSE_STATE.FAIL)
                }

                // 응답 성공시
                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                    Log.d(TAG, "gptImages - onResponse() called / respose : ${response.body()}")

                    when (response.code()) {
                        200 -> { // 정상 연결
                            completion(RESPONSE_STATE.OKAY)
                        }
                        else -> { // 에러
                            completion(RESPONSE_STATE.FAIL)
                        }
                    }
                }
            })
        }

    }

    // user-controller
    fun user(userId: Int, name: String, description: String, profileImgUrl: String, completion: (RESPONSE_STATE) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("userId", userId)
        jsonObject.addProperty("name", name)
        jsonObject.addProperty("description", description)
        jsonObject.addProperty("profileImgUrl", profileImgUrl)

        val call = iRetrofit?.gptImages(sentence = jsonObject) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "gptImages - onFailure() called / t: $t")
                var errorMessage: String = t.message.toString()
                t.printStackTrace();
                Log.e(TAG, "onFailure: $errorMessage")
                completion(RESPONSE_STATE.FAIL)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "gptImages - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> { // 에러
                        completion(RESPONSE_STATE.FAIL)
                    }
                }
            }
        })
    }

    fun userInfoUserid(
        userId: Int,
        completion: (RESPONSE_STATE, ArrayList<Post>?, ArrayList<Post>?, User?) -> Unit
    ) {
        val call = iRetrofit?.userInfoUserid(userId = userId) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, null, null, null)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        response.body()?.let {
                            var bookmarkPosts = ArrayList<Post>()
                            var myPost = ArrayList<Post>()

                            val body = it.asJsonObject
                            val data = body.get("data").asJsonObject
                            val bookmarkedPosts = data.getAsJsonArray("bookmarkedPosts")
                            val myPosts = data.getAsJsonArray("myPosts")

                            bookmarkedPosts.forEach { resultItem ->
                                val resultItemObject = resultItem.asJsonObject

                                val postNum = resultItemObject.get("postNum").asInt
                                val thumbnailImgUrl: String?

                                if (resultItemObject.get("thumbnailImgUrl").isJsonNull) {
                                    thumbnailImgUrl = null
                                } else thumbnailImgUrl =
                                    resultItemObject.get("thumbnailImgUrl").asString

                                val bookmark = resultItemObject.get("bookmark").asBoolean

                                val me: Boolean
                                if (resultItemObject.get("me").isJsonNull) {
                                    me = false
                                } else me = resultItemObject.get("bookmark").asBoolean

                                val postItem = Post(
                                    postNum = postNum,
                                    thumbnailImgUrl = API.BASE_URL + thumbnailImgUrl,
                                    bookmark = bookmark,
                                    me = me
                                )

                                bookmarkPosts.add(postItem)
                            }

                            myPosts.forEach { resultItem ->
                                val resultItemObject = resultItem.asJsonObject

                                val postNum = resultItemObject.get("postNum").asInt
                                val thumbnailImgUrl: String?

                                if (resultItemObject.get("thumbnailImgUrl").isJsonNull) {
                                    thumbnailImgUrl = null
                                } else thumbnailImgUrl =
                                    resultItemObject.get("thumbnailImgUrl").asString

                                val bookmark = resultItemObject.get("bookmark").asBoolean

                                val me: Boolean
                                if (resultItemObject.get("me").isJsonNull) {
                                    me = false
                                } else me = resultItemObject.get("me").asBoolean

                                val postItem = Post(
                                    postNum = postNum,
                                    thumbnailImgUrl = API.BASE_URL + thumbnailImgUrl,
                                    bookmark = bookmark,
                                    me = me
                                )

                                myPost.add(postItem)
                            }
                            val user = data.get("user").asJsonObject

                            val userId = user.get("userId").asInt
                            val name = user.get("name").asString
                            val email = user.get("email").asString

                            var description = user.get("description").asString


                            val profileImgUrl: String
                            if (user.get("profileImgUrl").isJsonNull) {
                                profileImgUrl = ""
                            } else profileImgUrl = user.get("profileImgUrl").asString

                            val postCount = user.get("postCount").asInt
                            val followerCount = user.get("followerCount").asInt
                            val followCount = user.get("followCount").asInt

                            val me: Boolean
                            if (user.get("isMe").isJsonNull) {
                                me = false
                            } else me = user.get("isMe").asBoolean


                            val userItem = User(
                                userId = userId,
                                name = name,
                                email = email,
                                description = description,
                                profileImgUrl = profileImgUrl,
                                postCount = postCount,
                                followerCount = followerCount,
                                followCount = followCount,
                                isMe = me
                            )

                            completion(RESPONSE_STATE.OKAY, bookmarkPosts, myPost, userItem)
                        }
                    }
                    else -> completion(RESPONSE_STATE.FAIL, null, null, null)
                }
            }
        })
    }

    fun userUserUserId(userId: Int, completion: (RESPONSE_STATE) -> Unit){
        val call = iRetrofit?.userUserUserId(userId = userId) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / respose : ${response.body()}")

                when (response.code()) {
                    200 -> { // 정상 연결
                        completion(RESPONSE_STATE.OKAY)
                    }
                    else -> completion(RESPONSE_STATE.FAIL)
                }
            }
        })
    }
}