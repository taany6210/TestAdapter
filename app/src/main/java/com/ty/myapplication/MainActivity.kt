package com.ty.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ty.myapplication.databinding.ActivityMainBinding

/**
 * @ 文件名:   MainActivity
 * @ 创建者:   ty
 * @ 时间:    2020/4/14 5:07 PM
 * @ 描述:
 */
class MainActivity : AppCompatActivity() {
    class PageInfo {
        var page: Int = 1
        fun nextPage() {
            page++
        }

        fun reset() {
            page = 1
        }

        fun isFirstPage(page: Int): Boolean {
            return page == 1
        }
    }

    private lateinit var adapter: TerminalListAdapter
    private var pageInfo: PageInfo = PageInfo()
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var terminalVM: TerminalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        terminalVM = ViewModelProviders.of(this).get(TerminalViewModel::class.java)

        initView()
        initAdapter()
        initListener()

        //数据请求
        refresh()

        terminalVM.pageTerminal.observe(this, Observer {
            if (null != it && it.list.isNotEmpty()) {
                adapter.loadMoreModule.isEnableLoadMore = true

                if (pageInfo.isFirstPage(it.page)) {
                    //如果是加载的第一页数据，用 setData()
                    adapter.setList(it.list)
                } else {
                    adapter.addData(it.list)
                }

                if (it.list.size < 10) {
                    //如果不够一页,显示没有更多数据布局
                    adapter.loadMoreModule.loadMoreEnd()
                } else {
                    adapter.loadMoreModule.loadMoreComplete()
                }
                // page加一
                pageInfo.nextPage()
            } else {
                adapter.loadMoreModule.isEnableLoadMore = false
                adapter.loadMoreModule.loadMoreEnd()
                adapter.setEmptyView(R.layout.layout_empty)
            }
        })
    }

    private fun request() {
        terminalVM.terminalList()
    }

    private fun initView() {

        mBinding.rvTerminal.layoutManager = LinearLayoutManager(this)
        mBinding.rvTerminal.addItemDecoration(
                DividerItemDecoration(
                        this,
                        DividerItemDecoration.VERTICAL
                )
        )
    }

    private fun initListener() {

        adapter.loadMoreModule.setOnLoadMoreListener {
            request()
        }
        adapter.loadMoreModule.isAutoLoadMore = true
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        adapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false


    }

    private fun refresh() {
        adapter.loadMoreModule.isEnableLoadMore = false
        pageInfo.reset()
        request()
    }

    private fun initAdapter() {
        adapter = TerminalListAdapter()
        adapter.animationEnable = true
        mBinding.rvTerminal.adapter = adapter
    }
}
