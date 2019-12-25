package com.changyu.celo.userList.view

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.changyu.celo.R
import com.changyu.celo.userList.model.bean.Result
import com.changyu.celo.userList.model.bean.UserInfoItem
import java.text.SimpleDateFormat
import java.util.*

/**
 * ClassName:   Adapter
 * Author:      changyu
 * Date:        2019/12/26 14:45
 */
class UserListAdapter(layoutResId: Int, data: List<Result>?)
    : BaseQuickAdapter<Result, BaseViewHolder>(layoutResId, data) {

    override fun convert(vh: BaseViewHolder, info: Result?) {
        if (info == null) return
        vh.setText(R.id.tv_title_name, info.title+""+info.name)
                .setText(R.id.tv_gender_DOB, "Gender: "+info.gender +"  DOB: "+ getDateTime(info.dateOfBirth.toString()))
                .addOnClickListener(R.id.rl_item_recommend)
//        Glide.with(mContext).load(info.images[0]).into(vh.getView(R.id.iv_story_image))
        Glide.with(mContext).load(info.image).into(vh.getView(R.id.iv_picture_image))
    }
    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s.toLong())
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}
