package com.jakeparker.coccocdemo.mapper

import com.jakeparker.coccocdemo.entity.ChannelEntity
import com.jakeparker.coccocdemo.model.Channel

/**
 * @author Long Tran
 * @since 02/01/2021
 */
object ChannelMapper {
    fun transform(channelEntity: ChannelEntity): Channel {
        return Channel(
            ItemMapper.transformCollection(channelEntity.items)
        )
    }
}