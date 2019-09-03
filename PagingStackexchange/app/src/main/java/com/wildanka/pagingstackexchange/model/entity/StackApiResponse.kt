package com.wildanka.pagingstackexchange.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StackApiResponse(
    @SerializedName("items")
    @Expose
    val items: List<Items> = mutableListOf(),
    val has_more: Boolean?
)

data class Items(
    @SerializedName("owner") val owner: Owner,
    @SerializedName("is_accepted") val isAccepted: Boolean,
    @SerializedName("score") val score: Int,
    @SerializedName("last_activity_date") val lastCreatedActivityDate: Long,
    @SerializedName("creation_date") val creationDate: Long,
    @SerializedName("answer_id") val answerId: Long,
    @SerializedName("question_id") val questionId: Long
)

data class Owner(
    @SerializedName("reputation") val reputation: Long,
    @SerializedName("user_id") val userId: Long,
    val user_type: String,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("display_name") val displayName: String,
    var link: String
)