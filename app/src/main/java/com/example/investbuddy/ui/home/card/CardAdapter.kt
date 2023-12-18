package com.example.investbuddy.ui.home.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.investbuddy.data.responses.StockResponse
import com.example.investbuddy.databinding.CardCellBinding

class CardAdapter(private val stocks: StockResponse,
                  private val clickListener: StockClickListener) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = stocks.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindStock(stocks[position])
    }


}