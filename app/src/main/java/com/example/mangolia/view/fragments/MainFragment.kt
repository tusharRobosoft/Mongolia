package com.example.mangolia.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.example.mangolia.utils.ItemDecorator
import org.koin.android.ext.android.get
import com.example.mangolia.databinding.FragmentMainBinding
import com.example.mangolia.models.Packages
import com.example.mangolia.utils.dpToPx
import com.example.mangolia.vewmodel.DashBoardViewModel
import com.example.mangolia.view.epoxy.controllers.DashboardActivityController


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel = get<DashBoardViewModel>()
        val epoxyController = DashboardActivityController()

        binding.epoxyRecyclerView.setController(epoxyController)
        binding.epoxyRecyclerView.addItemDecoration(ItemDecorator(0f,0f,15f.dpToPx(requireContext()),0f))

        // Attach visibility tracker to the RecyclerView to enable visibility events.
        EpoxyVisibilityTracker().attach(binding.epoxyRecyclerView)
        Carousel.setDefaultGlobalSnapHelperFactory(object : Carousel.SnapHelperFactory() {
            override fun buildSnapHelper(context: Context?): SnapHelper {
                return PagerSnapHelper()
            }
        })

        epoxyController.isLoading = true
        viewModel.getFeed()
        viewModel.dataFeed.observe(viewLifecycleOwner) {
            epoxyController.packageList = it.data?.curation?.packages as ArrayList<Packages>

        }
    }
}