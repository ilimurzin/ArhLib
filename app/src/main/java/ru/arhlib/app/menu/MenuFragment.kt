package ru.arhlib.app.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.arhlib.app.databinding.MenuFragmentBinding

class MenuFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = MenuFragmentBinding.inflate(inflater, container, false)
        binding.root.layoutManager = LinearLayoutManager(context)
        binding.root.adapter = MenuAdapter()
        return binding.root
    }
}
