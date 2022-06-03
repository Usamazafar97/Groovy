package petros.efthymiou.groovy.details

import retrofit2.http.GET
import retrofit2.http.Path

interface PlaylistDetailsAPI {

    // This get method will fetch the detail of a single object playlist from the server as a playlistDetails
    @GET("playlist-details/{id}")
    suspend fun fetchPlaylistDetails(@Path("id") id: String): PlaylistDetails

}
