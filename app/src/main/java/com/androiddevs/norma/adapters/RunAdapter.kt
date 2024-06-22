package com.androiddevs.norma.adapters

import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.norma.R
import com.androiddevs.norma.database.Run
import com.androiddevs.norma.other.TrackingUtility
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.Locale

class RunAdapter : RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    inner class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_run,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(run.img).into(findViewById(R.id.ivRunImage))

            val calendar = Calendar.getInstance().apply {
                timeInMillis = run.timestamp
            }
            val dateFormat = SimpleDateFormat("dd.MM.yy\nhh:mm:ss", Locale.getDefault())
            findViewById<MaterialTextView>(R.id.tvDate)?.text = dateFormat.format(calendar.time)

            val avgSpeed = "${run.avgSpeedInKmh}km/h"
            findViewById<MaterialTextView>(R.id.tvAvgSpeed)?.text = avgSpeed

            val distanceInKm = "${run.distanceInMeters / 1000f}km"
            findViewById<MaterialTextView>(R.id.tvDistance)?.text = distanceInKm

            findViewById<MaterialTextView>(R.id.tvTime)?.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

            val caloriesBurned = "${run.caloriesBurned}kcal"
            findViewById<MaterialTextView>(R.id.tvCalories)?.text = caloriesBurned
        }
    }
}