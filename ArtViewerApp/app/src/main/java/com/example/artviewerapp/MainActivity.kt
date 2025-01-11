import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.artviewerapp.data.repository.FavoritesRepository
import com.example.artviewerapp.ui.navigation.AppNavigation
import com.example.artviewerapp.ui.theme.ArtViewerAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize database repository
        FavoritesRepository.initialize(this)

        setContent {
            ArtViewerAppTheme {
                AppNavigation()
            }
        }
    }
}