package com.example.planapp.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planapp.R
import com.example.planapp.models.BrokerData
import com.example.planapp.utils.ImageDownloader


class HomeAdapter(private var brokers: MutableList<BrokerData> = mutableListOf()): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.card_operator_broker,
            parent,
            false
        )
        return HomeViewHolder(view)
    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(brokers[position])
    }
    override fun getItemCount(): Int {
        return brokers.size
    }
    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView? = null
        var fileImg: ImageView? = null
        init {
          name = itemView.findViewById<EditText>(R.id.broker_name)
          fileImg = itemView.findViewById(R.id.broker_backgroundImg)
            itemView.setOnClickListener {
                val context= itemView.context
                val intent = Intent(context, MemberPrinceActivity::class.java)
                context.startActivity(intent)
            }

        }

        fun bind(broker: BrokerData){
            with(itemView){
                name?.setText(broker.name.toString())

                    ImageDownloader.download(fileImg!!, "https://stc.ofuxico.com.br/img/upload/noticias/2020/11/18/bob-epsonja-pulando-em-frenye-a-sua-casa-esbanjando-felicidade_390112_36.jpg", 14)


            }

        }
    }
}

