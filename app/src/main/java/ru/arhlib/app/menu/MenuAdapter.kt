package ru.arhlib.app.menu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.arhlib.app.AboutActivity
import ru.arhlib.app.ContactsActivity
import ru.arhlib.app.R
import ru.arhlib.app.afisha.AfishaActivity
import ru.arhlib.app.browser.CustomTabs
import ru.arhlib.app.databinding.MenuItemBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private val menuItems = arrayListOf(
            MenuItem(R.drawable.ic_afisha, R.string.afisha) { v: View ->
                v.context.startActivity(Intent(v.context, AfishaActivity::class.java))
            },
            MenuItem(R.drawable.ic_catalog, R.string.catalog) { v: View ->
                CustomTabs.openUrl(v.context, v.context.getString(R.string.catalog_url))
            },
            MenuItem(R.drawable.ic_prolongation, R.string.prolongation) { v: View ->
                CustomTabs.openUrl(v.context, v.context.getString(R.string.prolongation_url))
            },
            MenuItem(R.drawable.ic_location, R.string.libraries_on_map) { v: View ->
                CustomTabs.openUrl(v.context, v.context.getString(R.string.map_url))
            },
            MenuItem(R.drawable.ic_question, R.string.virtual_help) { v: View ->
                CustomTabs.openUrl(v.context, v.context.getString(R.string.virtual_help_url))
            },
            MenuItem(R.drawable.ic_lawyer, R.string.ask_lawyer) { v: View ->
                CustomTabs.openUrl(v.context, v.context.getString(R.string.lawyer_url))
            },
            MenuItem(R.drawable.ic_contacts, R.string.contacts) { v: View ->
                v.context.startActivity(Intent(v.context, ContactsActivity::class.java))
            },
            MenuItem(R.drawable.ic_about, R.string.about) { v: View ->
                v.context.startActivity(Intent(v.context, AboutActivity::class.java))
            },
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuItems[position])
    }

    override fun getItemCount() = menuItems.size

    class ViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menuItem: MenuItem) {
            binding.icon.setImageResource(menuItem.iconResId)
            binding.name.setText(menuItem.nameResId)
            binding.root.setOnClickListener(menuItem.onClickListener)
        }
    }
}
