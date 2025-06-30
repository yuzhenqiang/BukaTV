package org.yzq.bukatv

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform