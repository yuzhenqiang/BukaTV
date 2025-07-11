package org.yzq.bukatv.lib

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.serialization.*

@Serializable
@XmlSerialName("multistatus", namespace = "DAV:", prefix = "d")
data class MultiStatus(
    @XmlElement(true)
    val response: List<WebDavResponse>
)

@Serializable
@XmlSerialName("response", namespace = "DAV:", prefix = "d")
data class WebDavResponse(
    @XmlElement(true)
    val href: String,

    @XmlElement(true)
    val propstat: PropStat
)

@Serializable
@XmlSerialName("propstat", namespace = "DAV:", prefix = "d")
data class PropStat(
    @XmlElement(true)
    val prop: WebDavProp,

    @XmlElement(true)
    val status: String
)

@Serializable
@XmlSerialName("prop", namespace = "DAV:", prefix = "d")
data class WebDavProp(
    @XmlElement(true)
    val displayname: String? = null,

    @XmlElement(true)
    val resourcetype: ResourceType? = null,

    @XmlElement(true)
    val getlastmodified: String? = null,
)

@Serializable
@XmlSerialName("resourcetype", namespace = "DAV:", prefix = "d")
data class ResourceType(
    @XmlElement(true)
    val collection: CollectionMarker? = null
)

@Serializable
@XmlSerialName("collection", namespace = "DAV:", prefix = "d")
object CollectionMarker

fun parsePropfindXml (xmlInput: String) {
    val format = XML() {
        autoPolymorphic = true
    }

    val parsed = format.decodeFromString<MultiStatus>(xmlInput)
    for (resp in parsed.response) {
        println("Path: ${resp.href}")
        println("Name: ${resp.propstat.prop.displayname}")
        println("Is Directory: ${resp.propstat.prop.resourcetype?.collection != null}")
    }
}