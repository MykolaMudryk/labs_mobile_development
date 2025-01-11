package di


import com.example.newsapp.ui.screens.artsByCategoryScreen.ArtsByCategoryScreenViewModel
import com.example.newsapp.ui.screens.searchScreen.SearchScreenViewModel
import data.server.ArtApiService
import ui.screens.mainScreen.MainScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.androidx.viewmodel.dsl.viewModel

private const val BASE_URL = "https://collectionapi.metmuseum.org/"

val DatabaseModule = module {

    /**
     * Initializing of the Retrofit instance, which generate API schema from ServerApi interface
     */

    single<ArtApiService> {
        val client = OkHttpClient() // unique client for server
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) // interceptor here just makes pretty logs of each request in the LogCat
        val clientBuilder: OkHttpClient.Builder = client.newBuilder().addInterceptor(interceptor)

        Retrofit.Builder()
            .baseUrl(BASE_URL)// base url set here
            .addConverterFactory(GsonConverterFactory.create()) // Gson converter factory converts pure json to your data classes
            .client(clientBuilder.build()) // add client here
            .build()
            .create(ArtApiService::class.java)// creating schema of requests by retrofit
    }
    viewModel { MainScreenViewModel(get()) }
    viewModel { (api: ArtApiService) -> ArtsByCategoryScreenViewModel(api) }
    viewModel { SearchScreenViewModel(get()) }

}