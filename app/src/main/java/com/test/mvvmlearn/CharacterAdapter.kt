package com.test.mvvmlearn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.test.mvvmlearn.network.Character

class CharacterAdapter(private val characterList: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewlHoder>() {

    inner class CharacterViewlHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(characterResult: Character) {
            val name = itemView.findViewById<TextView>(R.id.tvNameCharacter)
            val image = itemView.findViewById<ImageView>(R.id.imgCharacter)

            name.text = characterResult.name
            image.load(characterResult.image) {
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewlHoder {
        return CharacterViewlHoder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewlHoder, position: Int) {
        holder.bindData(characterList[position])
    }

    override fun getItemCount(): Int = characterList.size
}