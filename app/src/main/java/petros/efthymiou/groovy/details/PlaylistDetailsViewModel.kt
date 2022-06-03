package petros.efthymiou.groovy.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PlaylistDetailsViewModel(
    private val service: PlaylistDetailsService
) : ViewModel() {

    // livedata of playlist details for getting the list from the API
    val playlistDetails: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()

    // livedata of loader to check for the progress bar
    val loader = MutableLiveData<Boolean>()

    fun getPlaylistDetails(id: String) {

        // coroutine with the scope of viewModel
        viewModelScope.launch {

            // loader post value to be true at first
            loader.postValue(true)

            // fetching the playlist details on the basis of id
            service.fetchPlaylistDetails(id)

                // posting the value as false, as the data starts displaying
                .onEach {
                    loader.postValue(false)
                }

                // collects the data from flow and post to set the data of livedata
                .collect {
                    playlistDetails.postValue(it)
                }
        }
    }
}
