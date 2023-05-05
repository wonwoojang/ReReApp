package com.jww.rereapp.main.info.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jww.rereapp.base.BaseFragment
import com.jww.rereapp.databinding.FragmentInfoBinding
import com.jww.rereapp.extension.throttleClick

class InfoFragment : BaseFragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        binding.run {
            body.run {
                notice.throttleClick {
                    showToast("구현 중인 기능입니다.")
                }
                faq.throttleClick {
                    showToast("구현 중인 기능입니다.")
                }
                openLicense.throttleClick {
                    showToast("구현 중인 기능입니다.")
                }
                helper.throttleClick {
                    showToast("구현 중인 기능입니다.")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}