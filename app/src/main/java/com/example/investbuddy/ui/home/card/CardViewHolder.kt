package com.example.investbuddy.ui.home.card

import android.R
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.investbuddy.data.network.RemoteDataSource
import com.example.investbuddy.data.responses.StockResponseItem
import com.example.investbuddy.databinding.CardCellBinding
import com.example.investbuddy.ui.visible


class CardViewHolder(
    private val cardCellBinding: CardCellBinding,
    private val clickListener: StockClickListener
) : RecyclerView.ViewHolder(cardCellBinding.root) {

    fun bindStock(stock: StockResponseItem) {
        cardCellBinding.companyText.text = stock.company
        cardCellBinding.ticketText.text = stock.ticket
        cardCellBinding.priceText.text = stock.price.toString() + " ₽"
        val image = RemoteDataSource.BASE_URL + stock.image
        Glide.with(cardCellBinding.root).load(image).circleCrop()
            .into(cardCellBinding.imageView)

        if (stock.quantity != 0) {
            cardCellBinding.quantityText.text = "x " + stock.quantity.toString()
        } else {
            cardCellBinding.quantityText.visible(false)
        }

        cardCellBinding.cardView.setOnClickListener {
            clickListener.onClick(stock)
        }
    }
}