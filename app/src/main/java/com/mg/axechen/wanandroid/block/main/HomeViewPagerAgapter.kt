package com.mg.axechen.wanandroid.block.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by AxeChen on 2018/4/6.
 * 主页adapter
 */
class HomeViewPagerAgapter : FragmentPagerAdapter {

    var fragments: MutableList<Fragment>? = null

    constructor(fm: FragmentManager?, fragments: MutableList<Fragment>) : super(fm) {
        this.fragments = fragments
    }

    override fun getCount(): Int {
        return fragments!!.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments!!.get(position)
    }

}