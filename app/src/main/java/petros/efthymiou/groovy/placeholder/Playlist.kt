package petros.efthymiou.groovy.placeholder

import petros.efthymiou.groovy.R

// Ths model class is used for setting the recycler view
class Playlist(
    val id: String,
    val name: String,
    val category: String,

    // setting the default image as the playlist image
    val image: Int = R.drawable.playlist
)