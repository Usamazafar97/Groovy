package petros.efthymiou.groovy.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

// we made the viewModel factory because we want to send the service in a constructor
class PlaylistDetailsViewModelFactory @Inject constructor(
    private val service: PlaylistDetailsService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // returning the viewModel with repository
        return PlaylistDetailsViewModel(service) as T
    }

}
