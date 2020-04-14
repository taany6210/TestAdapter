package com.ty.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

/**
 * @ 文件名:   TerminalViewModel
 * @ 创建者:   ty
 * @ 时间:    2020/4/10 9:52 PM
 * @ 描述:     终端相关
 */
class TerminalViewModel : ViewModel() {

    var PAGE_SIZE = 10
    val pageTerminal = MutableLiveData<Page<Terminal>>()


    /**
     * 终端列表
     */
    fun terminalList() {
        val page = Page<Terminal>(1)
        thread {

            val listOf = mutableListOf<Terminal>()
            for (index in 1..10) {
                val terminal = Terminal("${index}-11", "${index}-啊啊啊啊啊啊啊", "${index}-啛啛喳喳错错错错错错")
                listOf.add(terminal)
            }

            page.list = listOf
            pageTerminal.postValue(page)


        }
    }

}