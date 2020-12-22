package com.practice.github_dagger2.binding

import androidx.databinding.BindingAdapter
import com.practice.github_dagger2.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

object ImageBindingAdapter{

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: CircleImageView?, url: String?) {
        if (url != null && url != "") Picasso.with(imageView?.context).load(url).placeholder(R.mipmap.ic_launcher).into(imageView)
    }

}