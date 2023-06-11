package com.dongminpark.projectgd.Retrofit


import com.dongminpark.projectgd.Utils.API
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface IRetrofit {
    // BookMark
    @POST(API.BOOKMARKS_CREATE_POSTNUM)
    fun bookmarksCreatePostnum(@Path("postNum") postNum: Int) : Call<JsonElement>

    @DELETE(API.BOOKMARKS_DELETE_POSTNUM)
    fun bookmarksDeletePostnum(@Path("postNum") postNum: Int) : Call<JsonElement>

    // comment-controller
    @POST(API.COMMENTS)
    fun comments(@Body CommentRequestDto: JsonObject) : Call<JsonElement>

    @DELETE(API.COMMENTS_SERIALNUM_DELETE)
    fun commentsSerialNumDelete(@Path("serialNum") serialNum: Int) : Call<JsonElement>

    @PUT(API.COMMENTS_SERIALNUM_UPDATE)
    fun commentsSerialNumUpdate(@Path("serialNum") serialNum: Int, @Body InputDto: JsonObject) : Call<JsonElement>

    @GET(API.COMMENT_LIST_POSTNUM)
    fun commentsListPostNum(@Path("postNum") postNum: Int, @Query("start")start: Int, @Query("display") display: Int) : Call<JsonElement>

    // Example
    @GET(API.EXAMPLE)
    fun example(): Call<JsonElement>

    // chat-gpt-controller
    @POST(API.GPT_IMAGES)
    fun gptImages(@Body sentence: JsonObject) : Call<JsonElement>

    @DELETE(API.GPT_STOP)
    fun gptStop(): Call<JsonElement>

    @POST(API.GPT_STORE)
    fun gptStore(@Path("serialNum") serialNum: Int): Call<JsonElement>

    // Follow
    @GET(API.FOLLOW_FOLLOWERS)
    fun followFollowersUserId(@Path("userId") userId: Int): Call<JsonElement>

    @POST(API.FOLLOW_FOLLOWING_FOLLOWINGID)
    fun followFollowingFollowingId(@Path("followingId") followingId: Int): Call<JsonElement>

    @GET(API.FOLLOW_FOLLOWINGS_USERID)
    fun followFollowingsUserId(@Path("userId") userId: Int): Call<JsonElement>

    @DELETE(API.FOLLOW_UNFoLLOWING_FOLLOWERID)
    fun followUnfollowingFollowerId(@Path("followerId") followerId: Int): Call<JsonElement>

    // Likes
    @POST(API.LIKES_CREATE_POSTNUM)
    fun likesCreatePostnum(@Path("postNum") postNum: Int) : Call<JsonElement>

    @DELETE(API.LIKES_DELETE_POSTNUM)
    fun likesDeletePostnum(@Path("postNum") postNum: Int) : Call<JsonElement>

    // post-controller
    @DELETE(API.POST_DELETE)
    fun postDelete(@Path("postNum") postNum: Int): Call<JsonElement>

    @GET(API.POST_FILTER)
    fun postFilter(@Path("tagName")tagName: String, @Query("start")start: Int, @Query("display") display: Int): Call<JsonElement>

    @GET(API.POST_LIST)
    fun postList(@Query("start")start: Int, @Query("display") display: Int): Call<JsonElement>

    @GET(API.POST_LIST_DETAIL)
    fun postListDetail(@Path("postNum") postNum: Int): Call<JsonElement>

    @GET(API.POST_POPULAR)
    fun postPopular(@Query("start")start: Int, @Query("display") display: Int): Call<JsonElement>

    @GET(API.POST_SEARCH)
    fun postSearch(@Query("start")start: Int, @Query("display") display: Int, @Query("keyword") keyword: String): Call<JsonElement>

    // user-controller
    @Multipart
    @POST(API.USER)
    fun user(@Body userDto: JsonObject): Call<JsonElement>

    @GET(API.USER_INFO_USERID)
    fun userInfoUserid(@Path("userId") userId: Int): Call<JsonElement>

    @POST(API.FIREBASE_CONNECT)
    fun firebaseConnect(@Path("uid") uid: String):Call<JsonElement>

    @Multipart
    @POST(API.UPLOAD)
    fun upload(@Part file: MultipartBody.Part): Call<JsonElement>

    @DELETE(API.USER_USER_USERID)
    fun userUserUserId(@Path("userId") userId: Int): Call<JsonElement>
}