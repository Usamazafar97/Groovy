package petros.efthymiou.groovy.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

// we made the viewModel factory because we want to send the repository in a constructor
class PlaylistViewModelFactory @Inject constructor(
    private val repository: PlaylistRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // returning the viewModel with repository
        return PlaylistViewModel(repository) as T
    }

}