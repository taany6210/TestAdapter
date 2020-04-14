package com.ty.myapplication

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.ty.myapplication.databinding.ItemTerminalListBinding

/**
 * @ 文件名:   TerminalListAdapter
 * @ 创建者:   ty
 * @ 时间:    2020/4/10 2:32 PM
 * @ 描述:    终端列表
 */
class TerminalListAdapter :
    BaseQuickAdapter<Terminal, BaseDataBindingHolder<ItemTerminalListBinding>>(R.layout.item_terminal_list),
    LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ItemTerminalListBinding>, item: Terminal) {
        holder.dataBinding?.terminal = item
        holder.dataBinding?.executePendingBindings()
    }
}
