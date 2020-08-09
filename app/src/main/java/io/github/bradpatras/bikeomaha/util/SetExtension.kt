package io.github.bradpatras.bikeomaha.util

fun Set<Map.Entry<String, Any?>>.toMap(): MutableMap<String, Any?> {
    return this.fold(mutableMapOf<String, Any?>(), { map, entry ->
        map.apply{ this[entry.key] = entry.value }
    })
}