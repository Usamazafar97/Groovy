package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.placeholder.Playlist
import javax.inject.Inject

// This class will convert the playlist Raw to playlist
class PlaylistMapper @Inject constructor() : Function1<List<PlaylistRaw>, List<Playlist>> {
    override fun invoke(playlistsRaw: List<PlaylistRaw>): List<Playlist> {

        // mapping the playlist, while checking the image if its a rock or the simple image, on the basis of the
        // category
        return playlistsRaw.map { playlistRaw ->
            val image = when (playlistRaw.category) {

                // if category is rock the select the rock image
                "rock" -> R.mipmap.rock

                // in other case, sets the image to default
                else -> R.mipmap.playlist
            }

            // setting the playlist with constructor, with the parameters as that of the playlistRaw
            Playlist(playlistRaw.id, playlistRaw.name, playlistRaw.category, image)
        }
    }

}
