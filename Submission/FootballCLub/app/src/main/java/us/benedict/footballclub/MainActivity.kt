/*
*
* Submission 1  : Aplikasi Football Club
* Author        : Benedict E. Pranata (benedict.erwin@gmail.com)
* cDate         : 5 September 2018
* Rev           : 5 - 10 September 2018
*
* */

package us.benedict.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import us.benedict.footballclub.adapter.RecyclerViewAdapter
import us.benedict.footballclub.layout.MainLayout
import us.benedict.footballclub.model.Item

class MainActivity : AppCompatActivity() {
    /* Lateinit mainLayout as MainLayout */
    lateinit var mainLayout : MainLayout

    /* ArrayList from model/Item */
    private var items: ArrayList<Item> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Set mainLayout as MainLayout */
        mainLayout = MainLayout()

        /* Set ContentView from MainLayout*/
        mainLayout.setContentView(this)

        /* Set list as RecycleView from MainLayout recyView */
        val list = mainLayout.recycleMain

        /* Call initData Function */
        initData()

        /* Set RecycleView layoutManager */
        list.layoutManager = LinearLayoutManager(this)

        /* Set RecycleView Adapter from items */
        list.adapter = RecyclerViewAdapter(applicationContext, items){

            /* Start DetailActivity and pass data */
            startActivity<DetailActivity>("item" to it)
        }

    }

    /* Function initData - populate items data */
    private fun initData(){
        /* Get value from strings.xml */
        val name = resources.getStringArray(R.array.clubNama)
        val image = resources.obtainTypedArray(R.array.clubImg)
        val desc = resources.getStringArray(R.array.clubDesc)

        items.clear()
        for (i in name.indices) {
            items.add(
                    Item(name[i],
                    image.getResourceId(i, 0),
                    desc[i])
            )
        }

        //Recycle the typed array
        image.recycle()
    }
}
