package data.entity.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total")
    val total: Int?,
    @SerializedName("objectIDs")
    val objectIDs: List<Int>?
)
