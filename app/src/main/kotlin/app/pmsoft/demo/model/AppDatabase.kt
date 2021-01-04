package app.pmsoft.demo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(
    entities = [
        Foo::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun fooDao(): FooDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "demo.db"

        private const val NUMBER_OF_THREADS = 4
        val databaseExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DB_NAME
                        ).allowMainThreadQueries()
                            .addCallback(
                            object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    GlobalScope.launch(Dispatchers.IO) {
                                        rePopulateDb(INSTANCE)
                                    }
                                }
                            }
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}

suspend fun rePopulateDb(database: AppDatabase?) {
    database?.let { db ->
        withContext(Dispatchers.IO) {
            db.fooDao().apply {
                deleteAll()
                insert(Foo(0, "Alpha"))
                insert(Foo(0, "Beta"))
            }
        }
    }
}
