package hu.robert.banyai.lmitest.presentation.common.extension

internal fun <E> ArrayList<E>.addItem(item: E): ArrayList<E> {
    add(item)
    return this
}

internal fun <E> ArrayList<E>.addAllItem(items: List<E>): ArrayList<E> {
    addAll(items)
    return this
}