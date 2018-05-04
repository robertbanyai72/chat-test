package hu.robert.banyai.lmitest.domain.resource

interface ResourceHelper {

    fun getString(stringId: Int): String
    fun dimensionInPixes(dimensionId: Int): Int

}