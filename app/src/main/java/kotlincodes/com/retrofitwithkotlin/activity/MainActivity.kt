package kotlincodes.com.retrofitwithkotlin.activity

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlincodes.com.retrofitwithkotlin.R
import kotlincodes.com.retrofitwithkotlin.adapters.DataAdpter
import kotlincodes.com.retrofitwithkotlin.model.Airport
import kotlincodes.com.retrofitwithkotlin.model.DataModel
import kotlincodes.com.retrofitwithkotlin.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<DataModel>()
    var airportList = ArrayList<Airport>()
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)

        //setting up the adapter
        recyclerView.adapter= DataAdpter(airportList,this)
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        progerssProgressDialog=ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()


//        getDat1a()

        getAirports()

        fab.setOnClickListener{
            intent = Intent(this, AddAirportActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getDat1a() {
        val call: Call<List<DataModel>> = ApiClient.getClient.getPhotos()
        call.enqueue(object : Callback<List<DataModel>> {

            override fun onResponse(call: Call<List<DataModel>>?, response: Response<List<DataModel>>?) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }

        })
    }

    private fun getAirports() {
        val call: Call<List<Airport>> = ApiClient.getClient.getAirports()
        call.enqueue(object : Callback<List<Airport>> {

            override fun onResponse(call: Call<List<Airport>>?, response: Response<List<Airport>>?) {
                progerssProgressDialog.dismiss()
                airportList.addAll(response!!.body()!!)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Airport>>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }

        })
    }


}

