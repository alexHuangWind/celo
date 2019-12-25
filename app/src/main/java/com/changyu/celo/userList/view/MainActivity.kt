package com.changyu.celo.userList.view

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.changyu.celo.R
import com.changyu.celo.manager.MyLoadMoreView
import com.changyu.celo.userList.model.bean.Result
import com.changyu.celo.userList.model.bean.UserInfoItem
import com.changyu.celo.userList.model.fetchUserInfo
import com.changyu.celo.widget.LoadingDialog
import kotlinx.android.synthetic.main.activity_user_list.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private val mMostDate = 4
    private var mDatePosition = 0
    private lateinit var mAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        initView()
        fetchUserInfoList()
    }



    private fun initView() {
        initPtr()
        initAdapter()

    }

    private fun initAdapter() {
        mAdapter = UserListAdapter(R.layout.item_user_info, null)
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mAdapter.setLoadMoreView(MyLoadMoreView())
        mAdapter.setOnItemChildClickListener { adapter, _, position ->
            val item: Result = adapter.getItem(position) as Result
            startUserDetailActivity(item)
        }
        mAdapter.setOnLoadMoreListener({
            if (mDatePosition < mMostDate - 1) {
                mDatePosition = ++mDatePosition
                fetchUserInfoList()
            } else {
                mAdapter.loadMoreEnd()
            }
        }, recyclerViewUInfo )

        (recyclerViewUInfo )?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        (recyclerViewUInfo )?.adapter = mAdapter
    }
    private fun startUserDetailActivity(res: Result) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("image",res.image)
        intent.putExtra("details",res.detail)
        intent.putExtra("name",res.name)
        startActivity(intent)

    }


    private fun initPtr() {
        ptrFrameOfUserList.disableWhenHorizontalMove(true)
        ptrFrameOfUserList.setPtrHandler(object : PtrDefaultHandler() {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                mDatePosition = 0
                fetchUserInfoList()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return !recyclerViewUInfo.canScrollVertically(-1)
            }
        })
    }

    private fun updateAdapter(userinfo: List<Result>) {
        if (mDatePosition == 0) {
            mAdapter.setNewData(userinfo)
        } else {
            mAdapter.addData(userinfo)

        }
    }
    private fun fetchUserInfoList() {
        fetchUserInfo()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<UserInfoItem>() {
                override fun onNext(item: UserInfoItem?) {
                    ptrFrameOfUserList?.refreshComplete()
                    if (item != null) {
                        updateAdapter(item.results);
                    }
                }

                override fun onCompleted() {
                    mAdapter.loadMoreComplete()
                }

                override fun onError(e: Throwable?) {
                    ptrFrameOfUserList?.refreshComplete()
                    e?.printStackTrace()
                    Timber.e(e)
                }
            })
    }
}
