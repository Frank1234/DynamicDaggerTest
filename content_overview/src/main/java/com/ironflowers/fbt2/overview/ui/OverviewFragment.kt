package com.ironflowers.fbt2.overview.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ironflowers.fbt2.overview.di.DaggerContentOverviewComponent
import com.ironflowers.fbt2.overview.ui.di.OverviewFragmentModule
import com.ironflowers.fbt2.overview.ui.vm.OverviewViewEvent
import com.ironflowers.fbt2.overview.ui.vm.OverviewViewModel
import com.ironflowers.fbt2.overview.ui.vm.OverviewViewState
import com.ironflowers.fbt2.ui.R
import com.ironflowers.fbt2.ui.base.coreComponent
import kotlinx.android.synthetic.main.overview_fragment.*
import javax.inject.Inject

class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModel: OverviewViewModel

    private lateinit var viewBinding: com.ironflowers.fbt2.ui.databinding.OverviewFragmentBinding

    private var callbacks: OverviewCallbacks? = null

    private val itemController by lazy { OverviewItemController(viewModel) }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        initDependencyInjection()

        callbacks = context as? OverviewCallbacks
            ?: throw IllegalStateException("Context needs to implement OverviewCallbacks: $context")
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.overview_fragment, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerViewBinding()
        viewModel.setup()
    }

    private fun initDependencyInjection() =
        DaggerContentOverviewComponent
            .builder()
            .coreComponent(coreComponent())
            .overviewFragmentModule(OverviewFragmentModule(this))
            .build()
            .inject(this)

    private fun setupRecyclerViewBinding() {
        recycler_view.setController(itemController)
        viewBinding.viewModel = viewModel
        viewBinding.setLifecycleOwner(viewLifecycleOwner)
        observeItemChanges()
        observeViewEvents()
    }

    private fun observeViewEvents() {
        viewModel.viewEvents.observe(viewLifecycleOwner, Observer { navigationRequest ->
            when (navigationRequest) {
                is OverviewViewEvent.NavigateToContentDetails -> navigateToDetails(navigationRequest.contentId)
            }
        })
    }

    private fun navigateToDetails(contentId: String) = callbacks?.onOverviewItemSelected(contentId)

    private fun observeItemChanges() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                is OverviewViewState.Loading -> {
                    val loadingMore = true
                    itemController.setData(listOf(), loadingMore)
                }
                is OverviewViewState.Success -> {
                    val loadingMore = false
                    itemController.setData(viewState.items, loadingMore)
                } // errors are shown using data binding
            }
        })
    }
}