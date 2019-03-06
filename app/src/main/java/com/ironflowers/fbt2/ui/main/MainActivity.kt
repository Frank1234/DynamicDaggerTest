package com.ironflowers.fbt2.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.ironflowers.fbt2.R
import com.ironflowers.fbt2.core.di.ContextModule
import com.ironflowers.fbt2.overview.ui.OverviewCallbacks
import com.ironflowers.fbt2.overview.ui.OverviewFragmentDirections
import com.ironflowers.fbt2.ui.extensions.coreComponent
import com.ironflowers.fbt2.ui.main.di.DaggerMainActivityComponent
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Module name of the dynamic feature.
 */
const val DYNAMIC_FEATURE_MODULE_CONTENT_DETAILS = "content_detail_feature"

class MainActivity : AppCompatActivity(), OverviewCallbacks {

    @Inject
    lateinit var splitInstallManager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencyInjection()
        super.onCreate(savedInstanceState)
        setContentView(com.ironflowers.fbt2.R.layout.main_activity)
    }

    override fun onOverviewItemSelected(contentId: String) = openContentDetailScreen(contentId)

    private fun initDependencyInjection() =
        DaggerMainActivityComponent
            .builder()
            .coreComponent(coreComponent())
            .build()
            .inject(this)

    /**
     * Opens the content detail screen. Installs the dynamic feature if needed.
     */
    private fun openContentDetailScreen(contentId: String) {

        // if the feature is already installed, it calls success right away:
        installContentModule().addOnSuccessListener {

            nav_host_fragment.findNavController().navigate(
                OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(contentId)
            )
        }
    }

    private fun installContentModule() = splitInstallManager.startInstall(
        SplitInstallRequest.newBuilder().addModule(DYNAMIC_FEATURE_MODULE_CONTENT_DETAILS).build()
    ).addOnFailureListener {
        onInstallContentModuleFailed(it)
    }

    private fun onInstallContentModuleFailed(error: Exception) {
        Timber.e(error, "Error installing module")
        Toast.makeText(
            this,
            getString(R.string.message_error_installing_module),
            Toast.LENGTH_LONG
        ).show()
    }
}
