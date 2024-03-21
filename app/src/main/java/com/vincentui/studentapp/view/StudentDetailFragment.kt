package com.vincentui.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vincentui.studentapp.R
import com.vincentui.studentapp.databinding.FragmentStudentDetailBinding
import com.vincentui.studentapp.model.Student
import com.vincentui.studentapp.viewmodel.DetailViewModel


class StudentDetailFragment : Fragment() {
    private lateinit var binding:FragmentStudentDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch()
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
            updateUI(student)
        })
    }
    private fun updateUI(student: Student) {
        binding.txtId.setText(student.id)
        binding.txtName.setText(student.name)
        binding.txtBod.setText(student.bod)
        binding.txtPhone.setText(student.phone)
        // Update ImageView using Glide or any other image loading library
    }
}