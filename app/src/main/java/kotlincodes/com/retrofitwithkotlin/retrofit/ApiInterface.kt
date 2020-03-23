package kotlincodes.com.retrofitwithkotlin.retrofit

import kotlincodes.com.retrofitwithkotlin.model.Airport
import kotlincodes.com.retrofitwithkotlin.model.DataModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("flights")
    fun getPhotos(): Call<List<DataModel>>

    @GET("airports")
    fun getAirports(): Call<List<Airport>>

    @FormUrlEncoded
    @POST("airports")
    fun storeAirport(
            @Field("iataCode") iataCode:String,
            @Field("city") city:String,
            @Field("state") state:String,
            @Header("Authorization") authHeader:String
    ): Call<Airport>

    @FormUrlEncoded
    @PUT("airports/14")
    fun updateAirport(
            @Field("iataCode") iataCode:String,
            @Field("city") city:String,
            @Field("state") state:String,
            @Header("Authorization") authHeader:String
    ): Call<Airport>

    @DELETE("airports/11")
    fun deleteAirport(@Header("Authorization") authHeader:String
    ): Call<ResponseBody>

}