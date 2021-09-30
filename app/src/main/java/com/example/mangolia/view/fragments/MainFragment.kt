package com.example.mangolia.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.get
import com.example.mangolia.databinding.FragmentMainBinding
import com.example.mangolia.models.Items
import com.example.mangolia.vewmodel.DashBoardViewModel
import com.example.mangolia.view.epoxy.controllers.DashboardActivityController
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel = get<DashBoardViewModel>()
        val epoxyController = DashboardActivityController()

        binding.epoxyRecyclerView.setController(epoxyController)
        epoxyController.isLoading = true
        viewModel.getFeed()
        viewModel.dataFeed.observe(viewLifecycleOwner) {
            epoxyController.itemList = it.data?.curation?.packages?.get(1)?.items as ArrayList<Items>

        }
    }
}