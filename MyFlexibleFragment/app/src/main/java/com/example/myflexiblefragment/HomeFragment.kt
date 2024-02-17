package com.example.myflexiblefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
class HomeFragment : Fragment(),View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // layout interface didefinisikan
        // dan ditransformasikan dari layout berupa file xml ke dalam objek view
        // dengan menggunakan metode inflate()

        // Inflater.inflate() merupakan objek dari LayoutInflater
        // yang berfungsi untuk mengubah layout xml ke dalam bentuk objek viewgroup atau widget
        // melalui pemanggilan metode inflate().
        // Sama seperti setContentView(<view>) pada Activity,
        // fungsi inflate di sini yaitu untuk menampilkan layout dari Fragment.
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // onViewCreated() yang akan bekerja setelah metode onCreateView()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCategory:Button = view.findViewById(R.id.btn_category)
        btnCategory.setOnClickListener(this)
    }

   override fun onClick(v:View) {
       if(v?.id == R.id.btn_category) {
           val categoryFragment = CategoryFragment()
           val fragmentManager = parentFragmentManager

           // jadikan CategoryFragment sebagai host berikutnya dan menjadi parentFragmentManget dan
           // childFragmentManger menjadi HomeFragment
           fragmentManager.beginTransaction().apply {
               replace(R.id.frame_container,categoryFragment,CategoryFragment::class.simpleName)
               addToBackStack(null)
               commit()
           }
       }
   }
}