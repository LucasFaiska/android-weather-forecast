package com.lfaiska.weather.presentation.scene.weather.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lfaiska.weather.domain.model.Weather
import com.lfaiska.weather.R
import com.lfaiska.weather.databinding.ViewWeatherListItemBinding

class WeatherListAdapter(
    private val onWeatherSelected: (weather: Weather) -> Unit
) : RecyclerView.Adapter<WeatherListAdapter.WeatherListViewHolder>() {

    private var weatherList: MutableList<Weather> = mutableListOf()

    fun updateWeatherList(weatherList: List<Weather>) {
        this.weatherList.clear()
        this.weatherList.addAll(weatherList)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val binding: ViewWeatherListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_weather_list_item,
            parent,
            false
        )

        return WeatherListViewHolder(
            binding
        )
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount() = weatherList.size

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.binding.weather = weatherList[position]
        holder.binding.root.setOnClickListener { onWeatherSelected.invoke(weatherList[position]) }
    }

    class WeatherListViewHolder(val binding: ViewWeatherListItemBinding) : ViewHolder(binding.root)
}