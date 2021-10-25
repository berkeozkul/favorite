package com.example.favoriteteam.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.favoriteteam.R
import com.example.favoriteteam.service.Players

class PlayersAdapter(
    var cntx: Context,
    var cardList: List<Players>,
    var clickListener: OnPlayersClickListener

) : RecyclerView.Adapter<PlayersAdapter.PLayersViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updatePlayers(newPlayers: List<Players>) {
        cardList = newPlayers
        notifyDataSetChanged()
    }

    //Adapter oluşturulduğunda ViwHolder'ı başlatan yerdir.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PLayersViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.football_card, parent, false)
    )

    // listemizin eleman sayısını veriyor.
    override fun getItemCount() = cardList.size

    //onCreateViewHolder'dan dönen verilerin bağlanma işlemini gerçekleştiriyor.
    override fun onBindViewHolder(holder: PlayersAdapter.PLayersViewHolder, position: Int) {
        holder.bind(cardList[position], clickListener, cntx)
    }

    // bu classta verilerimizi layouttaki datalara oturtuyoruz
    class PLayersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val playerImage: ImageView = itemView.findViewById(R.id.footballimage)
        private val playerName: TextView = itemView.findViewById(R.id.footballname)
        private val playerNo: TextView = itemView.findViewById(R.id.footballnumber)
        private val playerPosition: TextView = itemView.findViewById(R.id.footballposition)
        private val playerCountry: ImageView = itemView.findViewById(R.id.country)

        //itemviewa erişip verilerimizi aktarıyoruz.
        fun bind(players: Players, action: OnPlayersClickListener, cntx: Context) {

            Glide.with(cntx).load(players.playerphoto).into(playerImage);
            Glide.with(cntx).load(players.country).into(playerCountry);
            playerName.text = players.name
            playerNo.text = players.number
            playerPosition.text = players.position

            //itemview bizim liste elemanlarını recyclerview içinde şekillendirdiğimiz layout dosyasının inflate edilmiş haidir
            itemView.setOnClickListener {
                action.onItemClick(players)
            }
        }
    }
}

