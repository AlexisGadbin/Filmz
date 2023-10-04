package fr.eseo.e5e.ag.filmz.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.eseo.e5e.ag.filmz.databinding.CardRoleDetailsBinding
import fr.eseo.e5e.ag.filmz.model.entities.Artist
import fr.eseo.e5e.ag.filmz.model.entities.Role
import fr.eseo.e5e.ag.filmz.model.entities.RoleWithArtists

class MovieRoleViewAdapter(private val mocieId: Int) :
    ListAdapter<RoleWithArtists, MovieRoleViewAdapter.ViewHolder>(
        MovieRoleViewAdapter.RoleComparator()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    return ViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val current = getItem(position)
    holder.bind(current.role, current.artists.get(0))
  }

  class ViewHolder(val binding: CardRoleDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(role: Role, artist: Artist) {
      binding.roleArtist.text =
          artist.surname.uppercase() +
              " " +
              artist.forename.substring(0, 1).uppercase() +
              artist.forename.substring(1).lowercase()
      binding.roleTitle.text = role.roleTitle
    }

    companion object {
      fun create(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            CardRoleDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
      }
    }
  }

  class RoleComparator : DiffUtil.ItemCallback<RoleWithArtists>() {

    override fun areContentsTheSame(oldItem: RoleWithArtists, newItem: RoleWithArtists): Boolean {
      return oldItem.role.roleTitle == newItem.role.roleTitle
    }

    override fun areItemsTheSame(oldItem: RoleWithArtists, newItem: RoleWithArtists): Boolean {
      return oldItem === newItem
    }
  }
}
