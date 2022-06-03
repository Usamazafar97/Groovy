package petros.efthymiou.groovy.playlist

import retrofit2.http.GET

// This interface is basically for calling the methods from the server
interface PlaylistAPI {

    // This get method will fetch the playlist from the server as a list of playlistRaw
    @GET("playlists")
    suspend fun fetchAllPlaylists(): List<PlaylistRaw>

}
