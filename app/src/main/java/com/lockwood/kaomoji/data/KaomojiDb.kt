package com.lockwood.kaomoji.data

import com.lockwood.kaomoji.domain.datasource.KaomojiDataSource
import com.lockwood.kaomoji.domain.model.Kaomoji
import com.lockwood.kaomoji.domain.model.KaomojiList
import com.lockwood.kaomoji.extensions.clear
import com.lockwood.kaomoji.extensions.toVarargArray
import org.jetbrains.anko.db.insert

class KaomojiDb(private val kamomojiDbHelper: KaomojiDbHelper = KaomojiDbHelper.instance,
                private val dataMapper: DbDataMapper = DbDataMapper()) : KaomojiDataSource {

    override fun requestKaomojiByTypeId(typeId: Long): KaomojiList? = kamomojiDbHelper.use {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestKaomojiByType(type: String): KaomojiList? = kamomojiDbHelper.use {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestItemKaomoji(id: Long): Kaomoji? = kamomojiDbHelper.use {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestItemKaomojiByFavorite(isFavorite: Boolean): Kaomoji? = kamomojiDbHelper.use {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveKaomoji(kaomoji: KaomojiList) = kamomojiDbHelper.use {
        clear(TypeKaomojiTable.NAME)
        clear(ItemKaomojiTable.NAME)

        with(dataMapper.convertFromDomain(kaomoji)) {
            insert(TypeKaomojiTable.NAME, *map.toVarargArray())
            kaomojiList.forEach { insert(ItemKaomojiTable.NAME, *it.map.toVarargArray()) }
        }
    }

}