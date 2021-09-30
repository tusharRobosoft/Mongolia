package com.example.mangolia.view.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.divum.ibn.interfaces.OnFragmentInteractionListener
import com.example.mangolia.ItemDataClass
import com.example.mangolia.R
import com.example.mangolia.databinding.ActivityMainBinding
import com.example.mangolia.vewmodel.DashBoardViewModel
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {
//    private lateinit var recyclerView: EpoxyRecyclerView
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController =findNavController(R.id.nav_host_fragment)

       // recyclerView = findViewById(R.id.recycler_view)

        // Attach the visibility tracker to the RecyclerView. This will enable visibility events.
//        val epoxyVisibilityTracker = EpoxyVisibilityTracker()
//        epoxyVisibilityTracker.partialImpressionThresholdPercentage = 75
//        epoxyVisibilityTracker.attach(recyclerView)
//
//        val viewModel = get<DashBoardViewModel>()
//        viewModel.getFeed()
//        viewModel.dataFeed.observe(this) {
//            val items = it.data?.curation?.packages?.get(1)?.items
//            recyclerView.withModels {
//                items?.forEach { item ->
//                    item.source?.let { singleItem ->
//                        ItemDataClass(singleItem)
//                            .id("data class")
//                            .addTo(this)
//                    }
//                }
//            }
//        }
    }

    override fun gotoFragment(action: Int) {
        if (navController.currentDestination == null) {
            navController.navigate(action)
        } else {
            navController.currentDestination?.let {
                if (it.id != action) {
                    navController.navigate(action)
                }
            }
        }
    }

    override fun gotoFragment(action: Int, data: Bundle) {
        if (navController.currentDestination == null) {
            navController.navigate(action, data)
        } else {
            navController.currentDestination?.let {
                if (it.id != action) {
                    navController.navigate(action, data)
                }
            }
        }
    }

    override fun gotoFragment(navDirections: NavDirections) {
        navController.navigate(navDirections)
    }

    override fun goBack() {
        onBackPressed()
    }
}