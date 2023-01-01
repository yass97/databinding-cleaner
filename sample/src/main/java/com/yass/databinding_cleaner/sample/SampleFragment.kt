package com.yass.databinding_cleaner.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yass.databinding_cleaner.autoReleaseBinding
import com.yass.databinding_cleaner.sample.databinding.FragmentSampleBinding

class SampleFragment : Fragment(R.layout.fragment_sample) {

    private val binding by autoReleaseBinding<FragmentSampleBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sampleText.text = "AutoReleaseBinding"
    }

    companion object {
        fun newInstance() = SampleFragment()
    }
}
