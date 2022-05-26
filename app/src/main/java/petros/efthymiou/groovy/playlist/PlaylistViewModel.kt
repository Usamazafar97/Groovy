package petros.efthymiou.groovy.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.onEach
import petros.efthymiou.groovy.placeholder.Playlist

class PlaylistViewModel(private val repository: PlaylistRepository) : ViewModel() {

    val loader = MutableLiveData<Boolean>()
    val playlists = liveData<Result<List<Playlist>>> {

        loader.postValue(true)
        emitSource(

            repository.getPlaylists()
                .onEach {
                    loader.postValue(false)
                }
                .asLiveData()
        )
    }


    /*val playlists = MutableLiveData<Result<List<Playlist>>>()

    init {

        viewModelScope.launch() {
            repository.getPlaylists()
                .collect {
                    playlists.value = it
                }
        }

    }*/
}