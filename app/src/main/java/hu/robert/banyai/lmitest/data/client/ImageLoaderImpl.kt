package hu.robert.banyai.lmitest.data.client

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageLoaderImpl(private val context: Context) : ImageLoader {

    override fun loadImage(url: String, imageLoaderTarget: ImageLoaderTarget) {
        if (imageLoaderTarget !is ImageViewTarget) {
            throw IllegalStateException("Only ImageViewTarget is supported currently")
        }

        val options = RequestOptions.centerCropTransform().override(250, 250)

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageLoaderTarget.imageView)
    }

}