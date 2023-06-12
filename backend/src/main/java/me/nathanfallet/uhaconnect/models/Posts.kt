package me.nathanfallet.uhaconnect.models

import kotlinx.datetime.Instant
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object Posts : IntIdTable() {
    val user_id = reference("user_id", Users)
    val title = varchar("title", 32)
    val content = text("content")
    val date = long("date")
    val validated = bool("validated").default(false)
    val filename = varchar("filename", 256).nullable()

    override val primaryKey = PrimaryKey(id)

    fun toPost(row: ResultRow): Post {
        val user = if (row.hasValue(Users.id)) Users.toUser(row)
        else null
        val favorite =
            if (row.hasValue(Favorites.post_id) && row.getOrNull(Favorites.post_id) != null) Favorites.toFavorite(
                row
            )
            else null
        return Post(
            id = row[id].value,
            user_id = row[user_id].value,
            title = row[title],
            content = row[content],
            date = Instant.fromEpochMilliseconds(row[date]),
            validated = row[validated],
            filename = row[filename],
            user = user,
            favorite = favorite
        )
    }
}