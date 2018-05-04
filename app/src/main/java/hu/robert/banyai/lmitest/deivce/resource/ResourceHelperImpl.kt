package hu.robert.banyai.lmitest.deivce.resource

import android.content.res.Resources
import hu.robert.banyai.lmitest.domain.resource.ResourceHelper

class ResourceHelperImpl(private val resources: Resources) : ResourceHelper {

    override fun getString(stringId: Int): String {
        return resources.getString(stringId)
    }

    override fun dimensionInPixes(dimensionId: Int): Int {
        return resources.getDimension(dimensionId).toInt()
    }
}