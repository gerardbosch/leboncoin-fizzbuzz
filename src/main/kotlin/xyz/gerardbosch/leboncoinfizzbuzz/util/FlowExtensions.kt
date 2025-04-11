package xyz.gerardbosch.leboncoinfizzbuzz.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow

/** Intersperse the given delimiter element between the elements of the Flow. */
fun <T> Flow<T>.intersperse(delimiter: T): Flow<T> = flow {
    collectIndexed { ix, el ->
        if (ix > 0) emit(delimiter)
        emit(el)
    }
}
