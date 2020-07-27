package com.example.sewingandclothing.ui.order

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.sewingandclothing.R
import com.example.sewingandclothing.SewingAndClothingApplication
import kotlinx.android.synthetic.main.fragment_order.*
import javax.inject.Inject

class OrderFragment : Fragment(), OrderContract.View {

    @Inject
    lateinit var presenter: OrderContract.Presenter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        presenter.isUserOrSeamstress()
    }

    override fun onAttach(context: Context) {
        (activity?.application as SewingAndClothingApplication).getSickLeaveComponent().inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun popFragmentFromStack(){
        val options = NavOptions.Builder()
            .setPopUpTo(R.id.navigation_order, true)
            .build()

        findNavController().navigate(R.id.navigation_order_list, null,  options)
    }

    override fun showProgressBar(show: Boolean) {
        if (show){
            progressBarOrder.visibility = View.VISIBLE
        } else {
            progressBarOrder.visibility = View.INVISIBLE
        }
    }
}
