package com.meesam.springshoppingxml.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meesam.springshoppingxml.R
import com.meesam.springshoppingxml.databinding.FragmentMyFavoriteBinding

class MyFavoriteFragment : Fragment() {

  private lateinit var binding: FragmentMyFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


}