package com.ironflowers.fbt2.content_detail_feature.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ironflowers.fbt2.content_detail_feature.R
import com.ironflowers.fbt2.content_detail_feature.databinding.DetailFragmentBinding
import com.ironflowers.fbt2.content_detail_feature.di.DaggerContentDetailComponent
import com.ironflowers.fbt2.content_detail_feature.ui.di.DetailFragmentModule
import com.ironflowers.fbt2.content_detail_feature.ui.vm.DetailViewModel
import com.ironflowers.fbt2.ui.base.coreComponent
import javax.inject.Inject

class DetailFragment : Fragment() {

    private lateinit var viewBinding: DetailFragmentBinding

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        initDependencyInjection()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDataBinding()
    }

    private fun initDependencyInjection() =
        DaggerContentDetailComponent
            .builder()
            .coreComponent(coreComponent())
            .detailFragmentModule(DetailFragmentModule(this))
            .build()
            .inject(this)

    private fun initDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.setLifecycleOwner(viewLifecycleOwner)
        viewModel.setup()
    }
}