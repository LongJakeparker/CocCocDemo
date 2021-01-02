package com.jakeparker.coccocdemo.mapper

import com.jakeparker.coccocdemo.entity.ItemEntity
import com.jakeparker.coccocdemo.model.Item
import com.jakeparker.coccocdemo.util.CommonUtils
import org.jsoup.Jsoup

/**
 * @author Long Tran
 * @since 02/01/2021
 */
object ItemMapper {
    fun transform(itemEntity: ItemEntity): Item {
        return Item(
            itemEntity.title,
            getImageUrl(itemEntity.description),
            getContent(itemEntity.description),
            CommonUtils.parseDateTime(itemEntity.pubDate),
            itemEntity.link
        )
    }

    fun transformCollection(itemEntities: ArrayList<ItemEntity>?): ArrayList<Item> {
        if (itemEntities.isNullOrEmpty())
            return arrayListOf()
        val data = arrayListOf<Item>()
        for (entity in itemEntities) {
            data.add(transform(entity))
        }
        return data
    }

    private fun getImageUrl(htmlString: String?): String {
        if (htmlString.isNullOrEmpty())
            return ""

        var result = ""
        val doc = Jsoup.parse(htmlString)
        val element = doc?.selectFirst("img")
        if (element != null) {
            result = element.attr("src")
        }

        return result
    }

    private fun getContent(htmlString: String?): String {
        if (htmlString.isNullOrEmpty())
            return ""

        var result = ""
        val doc = Jsoup.parse(htmlString)
        val body = doc.body()
        if (body.childNodeSize() > 0) {
            result = body.childNode(body.childNodeSize() - 1).toString()
        }

        return result
    }
}