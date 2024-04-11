package com.vincentui.studentapp.view
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.vincentui.studentapp.R
import com.vincentui.studentapp.databinding.FragmentStudentDetailBinding
import com.vincentui.studentapp.model.Student
import com.vincentui.studentapp.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
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
        if(arguments != null) {
            val playerName =
                StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            detailViewModel.fetch(playerName)
        }
        observeStudent()
    }


    fun observeStudent() {
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var student = it

            binding.btnUpdate?.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_person_2_24)
                    }
            }
            binding.txtId.setText(detailViewModel.studentLD.value?.id)
            binding.txtName.setText(detailViewModel.studentLD.value?.name)
            binding.txtBod.setText(detailViewModel.studentLD.value?.bod)
            binding.txtPhone.setText(detailViewModel.studentLD.value?.phone)

            val picasso = this.context?.let { it1 -> Picasso.Builder(it1) }
            picasso?.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }
            picasso?.build()?.load(detailViewModel.studentLD.value?.photoUrl)?.into(binding.imageView3)
        })
    }
}