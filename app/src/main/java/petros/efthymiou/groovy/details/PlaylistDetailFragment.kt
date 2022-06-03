package petros.efthymiou.groovy.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist_detail.*
import petros.efthymiou.groovy.R
import javax.inject.Inject

// @AndroidEntryPoint enables members injection in your Application
@AndroidEntryPoint
class PlaylistDetailFragment : Fragment() {

    // declaring viewModel to call methods
    lateinit var viewModel: PlaylistDetailsViewModel

    // injecting the factory because viewModel uses viewModelFactory to initialize
    @Inject
    lateinit var viewModelFactory: PlaylistDetailsViewModelFactory

    // for getting the argument from the previous fragment
    private val args: PlaylistDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_playlist_detail, container, false)

        // getting the id from the args
        val id = args.playlistId

        // set up the view Model with the help of View model factory
        setUpViewModel()

        // getting the details from the view model
        viewModel.getPlaylistDetails(id)

        // observe playlist, in order to check the list is loaded or not
        observePlaylistDetails()

        // observe the loader to check if the list is still loaded or not
        observeLoader()

        return view
    }

    // This method will check the loader, while the details is loading from the API call
    private fun observeLoader() {

        // observing the livedata of the loader, if any changes happens
        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                // in-case the list is loading, view the loader
                true -> details_loader.visibility = View.VISIBLE

                // in-case the details are loaded, hide the loader
                else -> details_loader.visibility = View.GONE
            }
        }
    }

    // This method will check the playlist details, while the details is loading from the API call
    private fun observePlaylistDetails() {

        // observing the livedata, in-case any changes happens ( added or removed ) from the playlist details
        viewModel.playlistDetails.observe(this as LifecycleOwner) { playlistDetails ->

            // checks if the detail successfully loaded from the server, null if not
            if (playlistDetails.getOrNull() != null) {

                // setup the UI, in-case the detail loaded successfully
                setUpUI(playlistDetails)
            } else {

                // otherwise show the snack bar with info of something went wrong
                Snackbar.make(
                    playlists_details_root,
                    R.string.generic_error,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    // This method will setup the view model using factory
    private fun setUpViewModel() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(PlaylistDetailsViewModel::class.java)
    }

    // This method will sets the UI with the incoming playlist details
    private fun setUpUI(playlistDetails: Result<PlaylistDetails>) {
        playlist_details_name.text = playlistDetails.getOrNull()!!.name
        playlist_details_details.text = playlistDetails.getOrNull()!!.details
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlaylistDetailFragment()
    }
}