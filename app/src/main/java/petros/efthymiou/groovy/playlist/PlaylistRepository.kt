package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import petros.efthymiou.groovy.placeholder.Playlist
import javax.inject.Inject


class PlaylistRepository @Inject constructor(
    private val service: PlaylistService
) {

    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        val response = service.fetchPlaylists()
        return response
    }
}
