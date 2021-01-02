package com.jakeparker.coccocdemo.model

import java.io.Serializable

/**
 * @author Long Tran
 * @since 02/01/2021
 */
data class Item(
    val title: String?,
    val imageUrl: String?,
    val description: String?,
    val pubDate: String?,
    val link: String?
) : Serializable