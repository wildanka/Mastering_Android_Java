package com.wildanka.pagingstackexchange.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StackApiResponse(
    @SerializedName("items")
    @Expose
    val items: List<Items> = mutableListOf()
)

data class Items(
    @SerializedName("owner") val owner: Owner? = null,
    @SerializedName("is_accepted") val isAccepted: Boolean? = false,
    @SerializedName("score") val score: Int? = null,
    @SerializedName("last_activity_date") val lastCreatedActivityDate: Long? = null,
    @SerializedName("creation_date") val creationDate: Long? = null,
    @SerializedName("answer_id") val answerId: Long? = null,
    @SerializedName("question_id") val questionId: Long? = null
)

data class Owner(
    @SerializedName("reputation") val reputation: Long? = null,
    @SerializedName("user_id") val userId: Long? = null,
    val user_type: String? = null,
    @SerializedName("profile_image") val profileImage: String? = null,
    @SerializedName("display_name") val displayName: String? = null,
    var link: String? = null
)