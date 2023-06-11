package com.dongminpark.projectgd.Utils

object Constants {
    const val TAG: String = "로그"
}

object List{
    val TAGLIST = listOf(
        "TOP 30", "모던", "바우하우스", "미니멀", "내추럴",
        "북유럽", "젠", "러스틱", "앤틱", "로맨틱", "아르누보",
        "쉐비", "정크"
    )
}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

object USER{
    // 임시로 여기 적어둠. 나중에 보안을 위해서 local로 옮기거나 바꿔야함
    var ACCESS_TOKEN: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b2tlbiIsImZvbGxvd0NvdW50IjoiMCIsInJvbGUiOiJBRE1JTiIsInByb3ZpZGVyIjoiR09PR0xFIiwicHJvZmlsZUltZ1VybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS9wcm9maWxlLnBuZyIsIm5hbWUiOiJbMSA6IGFkbWluXSIsImRlc2NyaXB0aW9uIjoiVGhpcyBpcyB0aGUgYWRtaW4gdXNlciIsInBvc3RDb3VudCI6IjAiLCJ1c2VySWQiOiIxIiwiZm9sbG93ZXJDb3VudCI6IjAiLCJlbWFpbCI6ImFkbWluQGV4YW1wbGUuY29tIiwiaWF0IjoxNjg1NTMzMDY4LCJleHAiOjE3MTcxNTU0Njh9.rXmhh-RDdnXszwUKa-G7TsuB1zoa1M7CABwUwY8VxOU"
    var USERID: Int = 1
    // 프사 추가
    var NAME: String = ""
    var NAMETEMP = ""
    var DESCRIPTION : String = ""
    var DESCRIPTION_TEMP = ""
    var PROFILEIMGURL = ""
}

object API{
    const val BASE_URL = "http://api.ezipnaezip.life:8080"

    // BookMark
    const val BOOKMARKS_CREATE_POSTNUM = "/bookmarks/create/{postNum}"
    const val BOOKMARKS_DELETE_POSTNUM = "/bookmarks/delete/{postNum}"

    // chat-gpt-controller
    const val GPT_IMAGES = "/gpt/images"
    const val GPT_STOP = "/gpt/stop"
    const val GPT_STORE = "/gpt/store/{serialNum}"

    // comment-controller
    const val COMMENTS = "/comments"
    const val COMMENTS_SERIALNUM_DELETE = "/comments/{serialNum}/delete"
    const val COMMENTS_SERIALNUM_UPDATE = "/comments/{serialNum}/update"
    const val COMMENT_LIST_POSTNUM = "/comments/list/{postNum}"

    // Example
    const val EXAMPLE = "/example"

    // follow-controller
    const val FOLLOW_FOLLOWERS = "/follow/followers/{userId}"
    const val FOLLOW_FOLLOWING_FOLLOWINGID = "/follow/following/{followingId}"
    const val FOLLOW_FOLLOWINGS_USERID = "/follow/followings/{userId}"
    const val FOLLOW_UNFoLLOWING_FOLLOWERID = "/follow/unfollowing/{followerId}"

    // Likes
    const val LIKES_CREATE_POSTNUM = "/likes/create/{postNum}"
    const val LIKES_DELETE_POSTNUM = "/likes/delete/{postNum}"

    // post-controller
    const val POST_DELETE = "/posts/delete/{postNum}"
    const val POST_FILTER = "/posts/filter/{tagName}"
    const val POST_LIST = "/posts/list"
    const val POST_LIST_DETAIL = "/posts/list/{postNum}"
    const val POST_POPULAR = "/posts/popular"
    const val POST_SEARCH = "/posts/search"

    // user-controller
    const val USER = "/user"
    const val USER_INFO_USERID = "/user/info/{userId}"
    const val USER_LIST = "/user/list" // 얜 구현 안할듯
    const val USER_USER_USERID = "/user/user/{userId}"
    const val FIREBASE_CONNECT = "/user/firebase/{uid}"


    const val UPLOAD = "/upload"
}

object MESSAGE{
    const val ERROR = "오류가 발생했습니다. 다시 시도해주세요"
    const val SAVE = "저장이 완료되었습니다!"
    const val DELETE = "삭제가 완료되었습니다!"
}