package app.pmsoft.demo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "foos"
)
@Parcelize
class Foo(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "letter")
    var letter: String
) : Parcelable