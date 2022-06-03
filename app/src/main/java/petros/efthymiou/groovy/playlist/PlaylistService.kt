package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.placeholder.Playlist
import java.lang.RuntimeException
import javax.inject.Inject

// This service will use the api, for getting the data
class PlaylistService @Inject constructor(
    private val api: PlaylistAPI
) {

    // This method will fetch the list from the server with the help of API
    // This method will returns the result of playlistRaw as a flow
    suspend fun fetchPlaylists(): Flow<Result<List<PlaylistRaw>>> {
        return flow {

            // emits the successful result, that is fetching the playlist from the API
            emit(Result.success(api.fetchAllPlaylists()))
        }.catch {

            // in-case the result is failure, it throws exception, that enables the user to check the API call
            emit(Result.failure(RuntimeException("Something went wrong")))
//            emit(Result.success(fetchAllPlaylists()))
        }
    }

    // This method will return the playlist in-case if the api is not getting the data
    // This data is similar to data as on the server
    private fun fetchAllPlaylists(): List<Playlist> {
        val list = arrayListOf<Playlist>()
        list.add(
            Playlist(
                "1",
                "Hard Rock Cafe",
                "rock"
            )
        )
        list.add(
            Playlist(
                "2",
                "Chilled House",
                "house"
            )
        )
        list.add(
            Playlist(
                "3",
                "US TOP 40 HITS",
                "mixed"
            )
        )
        list.add(
            Playlist(
                "4",
                "90's Rock",
                "rock"
            )
        )
        list.add(
            Playlist(
                "5",
                "Purple Jazz",
                "jazz"
            )
        )
        list.add(
            Playlist(
                "6",
                "90's flashback",
                "pop"
            )
        )
        list.add(
            Playlist(
                "7",
                "Machine Funk",
                "electro"
            )
        )
        list.add(
            Playlist(
                "8",
                "Let's Groove",
                "mixed"
            )
        )
        list.add(
            Playlist(
                "9",
                "Feel The Beat",
                "electro"
            )
        )
        list.add(
            Playlist(
                "10",
                "Best Songs 2020",
                "mixed"
            )
        )

        return list

    }
}
