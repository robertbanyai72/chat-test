package hu.robert.banyai.lmitest.presentation.screen

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_image_adapter_item.view.*
import kotlinx.android.synthetic.main.view_owner_message_adapter_item.view.*
import kotlinx.android.synthetic.main.view_partner_message_adapter_item.view.*
import lmitest.banyai.robert.com.logmeintest.R
import hu.robert.banyai.lmitest.data.client.ImageLoader
import hu.robert.banyai.lmitest.data.client.ImageViewTarget
import hu.robert.banyai.lmitest.presentation.model.AdapterItemModel

class MainAdapter(private val imageLoader: ImageLoader) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<AdapterItemModel>()

    fun update(mData: ArrayList<AdapterItemModel>) {

        val diffCallback = MainAdapterDiffUtilCallback(data, mData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data.clear()
        data.addAll(mData)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            AdapterViewState.Owner.typeId -> {
                OwnerMessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_owner_message_adapter_item, parent, false))
            }
            AdapterViewState.Partner.typeId -> {
                PartnerMessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_partner_message_adapter_item, parent, false))
            }
            AdapterViewState.Joined.typeId -> {
                JoinedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_joined_adapter_item, parent, false))
            }
            AdapterViewState.Image.typeId -> {
                ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_image_adapter_item, parent, false))
            }
            else -> {
                throw IllegalStateException("Something wrong with view holder's view type")
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].adapterViewState.typeId

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PartnerMessageViewHolder -> holder.bind(data[holder.adapterPosition])
            is OwnerMessageViewHolder -> holder.bind(data[holder.adapterPosition])
            is ImageViewHolder -> holder.bind(data[holder.adapterPosition])
        }
    }

    inner class PartnerMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AdapterItemModel) {
            itemView.tvPartnerMessage.text = item.message
        }
    }

    inner class OwnerMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AdapterItemModel) {
            itemView.tvOwnerMessage.text = item.message
        }
    }

    inner class ImageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AdapterItemModel) {
            imageLoader.loadImage(item.imageUrl, ImageViewTarget(itemView.imgImage))
        }
    }

    inner class JoinedViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    inner class MainAdapterDiffUtilCallback(
            private val oldList: ArrayList<AdapterItemModel>,
            private val newList: ArrayList<AdapterItemModel>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}