package com.jakeparker.coccocdemo.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

/**
 * @author Long Tran
 * @since 02/01/2021
 */
@Root(name = "item")
data class ItemEntity @JvmOverloads constructor(
    @field:Element(name = "title")
    var title: String? = "",

    @field:Element(name = "description")
    var description: String? = "",

    @field:Element(name = "pubDate")
    var pubDate: String? = "",

    @field:Element(name = "link")
    var link: String? = "",

    @field:Element(name = "guid")
    var guid: String? = "",

    @field:Element(name = "comments")
    var comments: Int? = 0
) : Serializable