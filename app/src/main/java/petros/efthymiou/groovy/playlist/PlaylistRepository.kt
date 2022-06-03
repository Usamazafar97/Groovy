package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import petros.efthymiou.groovy.placeholder.Playlist
import javax.inject.Inject

// repository takes the service and mapper for converting from the playlistRaw to Playlist
class PlaylistRepository @Inject constructor(
    private val service: PlaylistService,
    private val mapper: PlaylistMapper

) {

    // This is a suspend method because it is used in coroutine
    // This method will get the playlistRaw from the service and map it to Playlist
    // This method will return the result as a flow
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {

        // convert the response from the playlist raw to playlist to be used in the recycler view
        val response = service.fetchPlaylists().map {

            // if the result is successful, then map it
            if (it.isSuccess)
                Result.success(mapper(it.getOrNull()!!))

            // otherwise through exception
            else
                Result.failure(it.exceptionOrNull()!!)
        }

        // returns the playlist
        return response
    }
}
