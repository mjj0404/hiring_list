package com.fetchreward.hiring.ui.setting

import android.os.Bundle
import android.view.*
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.fetchreward.hiring.R
import com.fetchreward.hiring.databinding.FragmentSettingBinding
import com.fetchreward.hiring.utility.Constant
import com.fetchreward.hiring.utility.PreferenceManager

class SettingsFragment : Fragment(), RadioGroup.OnCheckedChangeListener {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.themeRadioGroup.setOnCheckedChangeListener(this)

        when (PreferenceManager[Constant.THEME_SELECTION, 0]) {
            0 -> binding.themeDefault.isChecked = true
            1 -> binding.themeLight.isChecked = true
            2 -> binding.themeDark.isChecked = true
        }

        requireActivity().addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.menu_blank, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        })
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when (p1) {
            binding.themeDefault.id -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                PreferenceManager[Constant.THEME_SELECTION] = 0
            }
            binding.themeLight.id -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                PreferenceManager[Constant.THEME_SELECTION] = 1
            }
            binding.themeDark.id -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                PreferenceManager[Constant.THEME_SELECTION] = 2
            }
        }
    }
}