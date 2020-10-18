package com.example.memo.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.memo.R
import com.example.memo.ViewModelFactory
import com.example.memo.databinding.FragmentMemoDetailBinding
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class MemoDetailFragment : Fragment() {

    private lateinit var binding: FragmentMemoDetailBinding
    private val TAG = javaClass.simpleName
    private lateinit var viewModel: MemoDetailViewModel
    private val args: MemoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memo_detail, container, false)
        val application = requireNotNull(this.activity).application


        viewModel = ViewModelProvider(this, ViewModelFactory(application)).get(MemoDetailViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        getMemoId()

        return binding.root
    }

    private fun getMemoId() {
        val memoId = args.memoId
        Timber.d("memoId$memoId")
        binding.vm!!.getMemoById(memoId)

    }
}
