package com.example.savegram.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.savegram.Fragments.MusicFragment
import com.example.savegram.Fragments.PhotoFragment
import com.example.savegram.Fragments.VideoFragment

class DownloadViewPagerAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0){
            return VideoFragment()
        } else if (position == 1){
            return PhotoFragment()
        } else {
            return MusicFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0){
            return "Video"
        } else if(position == 1){
            return "Photo"
        } else {
            return "Music"
        }
    }
}