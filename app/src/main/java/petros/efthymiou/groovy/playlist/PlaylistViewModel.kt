package petros.efthymiou.groovy.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.onEach
import petros.efthymiou.groovy.placeholder.Playlist

class PlaylistViewModel(private val repository: PlaylistRepository) : ViewModel() {

    // livedata of loader to check for the progress bar
    val loader = MutableLiveData<Boolean>()

    // livedata of playlist for getting the list from the API
    val playlists = liveData<Result<List<Playlist>>> {

        // loader post value to be true at first
        loader.postValue(true)

        // with emitSource() you can not only emit a single value, but attach your
        // LiveData to another LiveData and start emitting from it.
        emitSource(

            // converting the playlist coming from the repository to livedata
            repository.getPlaylists()

                // posting the value as false, as the data starts displaying
                .onEach {
                    loader.postValue(false)
                }
                .asLiveData()
        )
    }

}