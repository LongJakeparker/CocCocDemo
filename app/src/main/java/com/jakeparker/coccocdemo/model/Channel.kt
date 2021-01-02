package com.jakeparker.coccocdemo.model

import java.io.Serializable

/**
 * @author Long Tran
 * @since 02/01/2021
 */
data class Channel(
    val items: ArrayList<Item>
): Serializable