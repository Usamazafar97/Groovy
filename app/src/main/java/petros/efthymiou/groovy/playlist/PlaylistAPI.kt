package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.placeholder.Playlist

interface PlaylistAPI {
    suspend fun fetchAllPlaylists() : List<Playlist>{
        TODO("Not yet implemented")
    }

}
