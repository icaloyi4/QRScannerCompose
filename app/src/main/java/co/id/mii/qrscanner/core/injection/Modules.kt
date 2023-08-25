package co.id.mii.qrscanner.core.injection

import android.content.Context
import androidx.room.Room
import co.id.mii.qrscanner.core.database.AppDatabase
import co.id.mii.qrscanner.core.database.DatabaseDao
import co.id.mii.qrscanner.core.database.PreferencesManager
import co.id.mii.qrscanner.core.network.ApiClient
import co.id.mii.qrscanner.core.network.ApiModel
import co.id.mii.qrscanner.features.home.repository.HomeRepository
import co.id.mii.qrscanner.features.home.viewmodel.HomeViewModel
import co.id.mii.qrscanner.features.payment.repository.PaymentRepository
import co.id.mii.qrscanner.features.payment.viewmodel.PaymentViewModel
import co.id.mii.qrscanner.features.promo.repository.PromoRepository
import co.id.mii.qrscanner.features.promo.viewmodel.PromoViewModel
import co.id.mii.qrscanner.features.transaction.repository.TransactionRepository
import co.id.mii.qrscanner.features.transaction.viewmodel.TransactionViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
    viewModel { TransactionViewModel(get()) }
    viewModel { PromoViewModel(get()) }
}


val apiModule = module {
    fun provideApi(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    single { provideApi(get()) }
}

val dataStoreModule = module {
    fun providePrefference(ctx: Context): PreferencesManager {
        return PreferencesManager(ctx)
    }
    single { providePrefference(androidContext()) }
}

val netModule = module {
    fun provideCache(application: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }


    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiModel.baseURL)
            .addConverterFactory(GsonConverterFactory.create(factory))
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())CoroutineCallAdapterFactoryΩ
            .client(client)
            .build()
    }

    single { provideCache(androidContext()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

}

val databaseModule = module {

    fun provideDatabase(application: Context): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "eds.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: AppDatabase): DatabaseDao {
        return database.databaseDao
    }

    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    fun provideHomeRepository(preff : PreferencesManager): HomeRepository {
        return HomeRepository(preff)
    }

    single { provideHomeRepository(get()) }

    fun providePaymentRepository(preff : PreferencesManager, databaseDao: DatabaseDao): PaymentRepository {
        return PaymentRepository(preff, databaseDao)
    }

    single { providePaymentRepository(get(), get()) }

    fun provideTransactionRepository( databaseDao: DatabaseDao): TransactionRepository {
        return TransactionRepository(databaseDao)
    }

    single { provideTransactionRepository( get()) }

    fun providePromoRepository(apiClient: ApiClient): PromoRepository {
        return PromoRepository(apiClient)
    }

    single { providePromoRepository( get()) }
}