package petros.efthymiou.groovy.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist.*
import okhttp3.OkHttpClient
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.placeholder.Playlist
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

// @AndroidEntryPoint enables members injection in your Application
@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    // declaring viewModel to call methods
    lateinit var viewModel: PlaylistViewModel

    // injecting the factory because viewModel uses viewModelFactory to initialize
    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        // set up the view Model with the help of View model factory
        setUpViewModel()

        // observe the loader to check if the list is still loaded or not
        observeLoader()

        // observe playlist, in order to check the list is loaded or not
        observePlaylists(view)

        return view
    }

    // This method will check the loader, while the playlist is loading from the API call
    private fun observeLoader() {

        // observing the livedata of the loader, if any changes happens
        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                // in-case the list is loading, view the loader
                true -> loader.visibility = View.VISIBLE

                // in-case the list is loaded, hide the loader
                else -> loader.visibility = View.GONE
            }
        }
    }

    // This method will check the playlist, while the playlist is loading from the API call
    private fun observePlaylists(view: View) {

        // observing the livedata, in-case any changes happens ( added or removed ) from the playlist
        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->

            // checks if the list successfully loaded from the server, null if not
            if (playlists.getOrNull() != null) {

                // setup the recycler view, in-case the list loaded successfully
                setUpList(view.findViewById(R.id.playlists_list), playlists.getOrNull()!!)
            } else {

            }
        }
    }

    // This method will sets the recycler view with the incoming playlist
    private fun setUpList(
        view: View?,
        playlists: List<Playlist>
    ) {

        // incoming view of recyclerView
        with(view as RecyclerView) {

            // setting up the manager as Vertical Linear layout
            layoutManager = LinearLayoutManager(context)

            // setting the adapter, with lambda function of click listener to go on the next fragment
            adapter = MyPlaylistRecyclerViewAdapter(playlists) { id ->
                val action =
                    PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment(id)

                // navigating to the next fragment
                findNavController().navigate(action)
            }
        }
    }

    // This method will setup the view model using factory
    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {

            }
    }
}