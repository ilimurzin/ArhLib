package ru.arhlib.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import ru.arhlib.app.actual.ActualFragment
import ru.arhlib.app.databinding.MainActivityBinding
import ru.arhlib.app.menu.MenuFragment
import ru.arhlib.app.news.PostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.pager
        val tabLayout = binding.tabs

        viewPager.adapter = SectionsPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Новости"
                1 -> "Актуальноe"
                else -> "Сервисы"
            }
        }.attach()
    }

    private class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> PostFragment()
                1 -> ActualFragment()
                else -> MenuFragment()
            }
        }

        override fun getItemCount(): Int = 3
    }
}
