package petros.efthymiou.groovy.details


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

// This service will use the api, for getting the data
class PlaylistDetailsService @Inject constructor(
    private val api: PlaylistDetailsAPI
) {

    // This method will fetch the details from the server with the help of API
    // This method will returns the result of PlaylistDetails as a flow
    suspend fun fetchPlaylistDetails(id: String): Flow<Result<PlaylistDetails>> {
        return flow {
            // emits the successful result, that is fetching the playlist details from the API
            emit(Result.success(api.fetchPlaylistDetails(id)))
        }.catch {

            // in-case the result is failure, it throws exception, that enables the user to check the API call
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
