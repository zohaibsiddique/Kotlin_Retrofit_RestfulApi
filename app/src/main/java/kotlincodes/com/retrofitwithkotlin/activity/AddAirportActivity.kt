package kotlincodes.com.retrofitwithkotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlincodes.com.retrofitwithkotlin.R
import kotlincodes.com.retrofitwithkotlin.model.Airport
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_add_airport.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAirportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_airport)

        val code = findViewById<EditText>(R.id.code)
        val city = findViewById<EditText>(R.id.city)
        val state = findViewById<EditText>(R.id.state)

        savebtn.setOnClickListener{
            val call: Call<Airport> = ApiClient.getClient.storeAirport(code.text.toString(), city.text.toString(), state.text.toString())
            call.enqueue(object : Callback<Airport> {

                override fun onResponse(call: Call<Airport>?, response: Response<Airport>?) {
                    Toast.makeText(applicationContext, response?.body()?.city,Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Airport>?, t: Throwable?) {
                    Toast.makeText(applicationContext, t?.message,Toast.LENGTH_LONG).show()
                }

            })

        }
    }
}
