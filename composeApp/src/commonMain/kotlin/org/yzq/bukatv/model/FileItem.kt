package org.yzq.bukatv.model

enum class SourceType {
    WebDav,
    Disk
}

data class FileItem(
    val sourceType: SourceType,
    val name: String,
    val path: String,
    val isDir: Boolean,
)
