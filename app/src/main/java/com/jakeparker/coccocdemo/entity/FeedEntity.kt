package com.jakeparker.coccocdemo.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

/**
 * @author Long Tran
 * @since 02/01/2021
 */
@Root(name = "rss", strict = false)
data class FeedEntity @JvmOverloads constructor(
    @field:Element(name = "channel")
    var channel: ChannelEntity? = null
): Serializable