package com.ahan.storagebox.adapter.ScreenAdapter

import com.ahan.storagebox.R
import com.ahan.storagebox.model.ScreenBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class ScreenAdapter(listData: ArrayList<ScreenBean>) :BaseQuickAdapter<ScreenBean,BaseViewHolder>
    (R.layout.layout_item_cell,listData){
    override fun convert(holder: BaseViewHolder, item: ScreenBean) {
        holder.setText(R.id.title,item.title)
        holder.setText(R.id.content,item.content)
    }
}
