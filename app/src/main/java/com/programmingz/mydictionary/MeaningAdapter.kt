package com.programmingz.mydictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.programmingz.mydictionary.databinding.MeaningRecyclerViewBinding

class MeaningAdapter(private var meaningList: List<Meaning>) :RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {

    class MeaningViewHolder(private val binding: MeaningRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(meaning: Meaning){
            binding.partOfSpeechTextview.text = meaning.partOfSpeech
            binding.definitionTextview.text = meaning.definitions.joinToString("\n\n") {
                var currentIndex = meaning.definitions.indexOf(it)
                (currentIndex+1).toString() + ". " + it.definition.toString()
            }
            if (meaning.synonyms.isEmpty()){
                binding.synonymsTitle.visibility = View.GONE
                binding.synonymsTextview.visibility = View.GONE
            }else {
                binding.synonymsTitle.visibility = View.VISIBLE
                binding.synonymsTextview.visibility = View.VISIBLE
                binding.synonymsTextview.text = meaning.synonyms.joinToString (", ")
            }


            if (meaning.antonyms.isEmpty()){
                binding.antonymsTitle.visibility = View.GONE
                binding.antonymsTextview.visibility = View.GONE
            }else {
                binding.antonymsTitle.visibility = View.VISIBLE
                binding.antonymsTextview.visibility = View.VISIBLE
                binding.antonymsTextview.text = meaning.antonyms.joinToString (", ")
            }

        }
    }

    fun updateNewData(newMeaning: List<Meaning>){
        meaningList = newMeaning
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = MeaningRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}