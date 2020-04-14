package com.ty.myapplication


/**
 * @ 文件名:   PageTerminal
 * @ 创建者:   ty
 * @ 时间:    2020/4/10 9:59 PM
 * @ 描述:
 */
data class Page<T>(var page: Int) {
    var is_admin: Int = 0
    var count: Int = 0
    var size: Int = 0
    var total_page: Int = 0
    var list: Collection<T> = mutableListOf()
        get() = if (field == null) {
            mutableListOf()
        } else {
            field
        }
}