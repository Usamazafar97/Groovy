package petros.efthymiou.groovy

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {


    val mActivityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get


    @Test
    fun displayScreenTitle() {

        // checking the title bar
        assertDisplayed("Playlists")
    }

    @Test
    fun displayListOfPlaylists(){

        //assertRecyclerViewItemCount(R.id.playlists_list, 10)
    }
}