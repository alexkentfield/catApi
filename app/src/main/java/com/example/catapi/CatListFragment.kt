package com.example.catapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_catlist.*

class CatListFragment : Fragment() {

    private var catViewModel: CatViewModel? = null
    lateinit var cats: java.util.ArrayList<CatDO>

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object {
        private const val FILTER_ARG = "com.example.catapi.CatListFragment.filter"

        fun newInstance(filter : CatFilters): CatListFragment {
            val args = Bundle()
            args.putSerializable(FILTER_ARG, filter)
            val fragment = CatListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catlist, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val secretValue = getString(R.string.cat_api_key)
        val userId = getString(R.string.cat_id)
        val filter = arguments?.getSerializable(FILTER_ARG)

        viewManager = LinearLayoutManager(activity?.baseContext)
        catViewModel = ViewModelProviders.of(this).get(CatViewModel::class.java)
        catViewModel!!.init(secretValue, userId)

        if (filter == CatFilters.ALL) {
            catViewModel!!.getAllCats()?.observe(this, Observer {
                cats = it as ArrayList<CatDO>
                initializeView()
            })
        } else if (filter == CatFilters.FAVORITES) {
            catViewModel!!.getFavoriteCats().observe(this, Observer {
                cats = it as ArrayList<CatDO>
                initializeView()
            })
        }
    }

    private fun initializeView() {
        viewAdapter = CatAdapter(cats, catViewModel!!, activity!!.baseContext)

        recyclerView = rv_cats.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}