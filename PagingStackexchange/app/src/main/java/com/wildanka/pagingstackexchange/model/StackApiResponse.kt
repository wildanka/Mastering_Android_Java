package com.wildanka.pagingstackexchange.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class StackApiResponse {
    @SerializedName("items")
    @Expose
    open var items: List<Items> = mutableListOf()
}

open class Items {
    @SerializedName("owner")
    @Expose
    open var owner: Owner? = null

    @SerializedName("is_accepted")
    @Expose
    open var isAccepted: Boolean? = false

    @SerializedName("score")
    @Expose
    open var score: Int? = null

    @SerializedName("last_activity_date")
    @Expose
    open var lastCreatedActivityDate: Long? = null

    @SerializedName("creation_date")
    @Expose
    open var creationDate: Long? = null

    @SerializedName("answer_id")
    @Expose
    open var answerId: Long? = null

    @SerializedName("question_id")
    @Expose
    open var questionId: Long? = null

}

open class Owner {
    @SerializedName("reputation")
    @Expose
    open var reputation: Long? = null

    @SerializedName("user_id")
    @Expose
    open var userId: Long? = null

    open var user_type: String? = null

    @SerializedName("profile_image")
    @Expose
    open var profileImage: String? = null

    @SerializedName("display_name")
    @Expose
    open var displayName: String? = null

    open var link: String? = null

}