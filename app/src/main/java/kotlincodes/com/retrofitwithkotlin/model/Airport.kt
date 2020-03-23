package kotlincodes.com.retrofitwithkotlin.model

import com.google.gson.annotations.SerializedName


data class Airport(

        @SerializedName("state")
        val status: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("iataCode")
        val code: String)