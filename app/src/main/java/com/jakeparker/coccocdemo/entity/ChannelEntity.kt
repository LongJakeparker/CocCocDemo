package com.jakeparker.coccocdemo.entity

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable

/**
 * @author Long Tran
 * @since 02/01/2021
 */
@Root(name = "channel", strict = false)
data class ChannelEntity @JvmOverloads constructor(
    @field:ElementList(inline = true, name = "item")
    var items: ArrayList<ItemEntity>? = null
): Serializable