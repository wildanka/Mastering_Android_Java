package com.wildanka.pagingstackexchange

import com.google.gson.annotations.SerializedName

class StackApiResponse {
    var items: Items? = null
}

class Items {
    var owner: Owner? = null
    @SerializedName("is_accepted")
    var isAccepted: Boolean? = false
    var score: Int? = null
    @SerializedName("last_activity_date")
    var lastCreatedActivityDate: Long? = null
    var creationDate: Long? = null
    var answerId: Long? = null
    var questionId: Long? = null

}

class Owner {
    var reputation: Long? = null
    var userId: Long? = null
    var userType: String? = null
    var profileImage: String? = null
    var displayName: String? = null
    var link: String? = null

}