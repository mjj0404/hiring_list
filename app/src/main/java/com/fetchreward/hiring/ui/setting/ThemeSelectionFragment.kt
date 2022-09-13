package com.fetchreward.hiring.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.fetchreward.hiring.HiringListViewModel
import com.fetchreward.hiring.R
import com.fetchreward.hiring.databinding.FragmentMainBinding
import com.fetchreward.hiring.databinding.FragmentThemeSelectionBinding
import com.fetchreward.hiring.ui.list.HiringListFragment
import com.fetchreward.hiring.utility.Constant
import com.fetchreward.hiring.utility.PreferenceManager

class ThemeSelectionFragment : Fragment() {

    private var binding: FragmentThemeSelectionBinding? = null

    companion object {
        fun newInstance() = ThemeSelectionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentThemeSelectionBinding.inflate(inflater, container, false);
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val radioGroup = binding!!.themeRadioGroup
        val defaultRadioButton = binding!!.themeDefault
        val lightRadioButton = binding!!.themeLight
        val darkRadioButton = binding!!.themeDark
//        PreferenceManager.preferences.edit().clear().apply()

        when (PreferenceManager[Constant.THEME_SELECTION, 0]) {
            0 -> defaultRadioButton.isChecked = true
            1 -> lightRadioButton.isChecked = true
            2 -> darkRadioButton.isChecked = true
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                defaultRadioButton.id -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    PreferenceManager[Constant.THEME_SELECTION] = 0
                }
                lightRadioButton.id -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    PreferenceManager[Constant.THEME_SELECTION] = 1
                }
                darkRadioButton.id -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    PreferenceManager[Constant.THEME_SELECTION] = 2
                }
            }
        }
    }

}