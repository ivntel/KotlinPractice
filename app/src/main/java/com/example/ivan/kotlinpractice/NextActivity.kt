package com.example.ivan.kotlinpractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.ivan.kotlinpractice.Network.NetworkService
import com.example.ivan.kotlinpractice.objects.Model
import com.example.ivan.kotlinpractice.objects.Person
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {
    var disposable: Disposable? = null
    var searchItems: List<Model.Search> = ArrayList()
    private var adapter: TItleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        //Get object from intent
        var personFromMain = intent.extras.getSerializable("Tag") as? Person
        var firstName = personFromMain?.firstName
        var lastName = personFromMain?.lastName

        //Set TextView
        fullName.text = "$firstName $lastName"
        //Make api call with parameter passed in
        beginSearch("Miami")
    }

        fun beginSearch(srsearch: String) {

            NetworkService
                    .serviceInstance
                    .hitCountCheck("query", "json", "search", srsearch)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> showResult(result.query.searchinfo.totalhits, result) },
                            { error -> showError(error.message) }
                    )
            //1st Api Call
            /*val wikiApiServe by lazy {
                WikiApiService.create()
            }
            disposable = wikiApiServe.hitCountCheck("query", "json", "search", srsearch)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    { result -> showResult(result.query.searchinfo.totalhits, result) },
                                    { error -> showError(error.message) }
                            )*/
        }

    fun showResult(hitsCount: Int, searchInfo: Model.Result){
        //Populate TextView
        totalHitsCount.text = hitsCount.toString()
        //Populate RecyclerView
        searchItems = searchInfo.query.search
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        adapter = TItleAdapter (this, searchItems)
        recyclerView.adapter = adapter
    }

    fun showError(message: String?) {
        totalHitsCount.text = message
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
