package hu.robert.banyai.lmitest.data.client

interface ImageLoader{
    fun loadImage(url : String, imageLoaderTarget: ImageLoaderTarget)
}