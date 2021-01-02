package com.jakeparker.coccocdemo.mapper

import com.jakeparker.coccocdemo.entity.FeedEntity
import com.jakeparker.coccocdemo.model.Feed

/**
 * @author Long Tran
 * @since 02/01/2021
 */
object FeedMapper {
    fun transform(feedEntity: FeedEntity): Feed {
        return Feed(
            feedEntity.channel?.let { ChannelMapper.transform(it) }
        )
    }
}