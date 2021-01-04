package app.pmsoft.demo.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FooDao {

    @Query("DELETE FROM foos")
    fun deleteAll()

    @Insert
    fun insert(foo: Foo): Long

    @Query("SELECT * FROM foos")
    fun getAll(): List<Foo>
}