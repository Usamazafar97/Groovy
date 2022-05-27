package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import petros.efthymiou.groovy.placeholder.Playlist
import javax.inject.Inject


class PlaylistRepository @Inject constructor(
    private val service: PlaylistService,
//    private val mapper: PlaylistMapper
) {

    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        val response = service.fetchPlaylists()
//            .map {
//            Result.success(mapper(it.getOrNull()!!))
//        }
        return response
    }
}
