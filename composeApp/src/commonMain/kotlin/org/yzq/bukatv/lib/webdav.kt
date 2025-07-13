package org.yzq.bukatv.lib

import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.util.encodeBase64
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import nl.adaptivity.xmlutil.ExperimentalXmlUtilApi
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlConfig
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import org.yzq.bukatv.model.FileItem
import org.yzq.bukatv.model.SourceType

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

@OptIn(ExperimentalXmlUtilApi::class)
private fun parsePropfindXml(xmlInput: String): List<FileItem> {
    val format = XML() {
        autoPolymorphic = true
        defaultPolicy {
            unknownChildHandler = XmlConfig.IGNORING_UNKNOWN_CHILD_HANDLER
        }
    }

    val parsed = format.decodeFromString<MultiStatus>(xmlInput)
    val files = mutableListOf<FileItem>()
    for (resp in parsed.response) {
        val file = FileItem(
            sourceType = SourceType.WebDav,
            path = resp.href,
            name = resp.href.split("/").last { it.isNotEmpty() },
            isDir = resp.propstat.prop.resourcetype?.collection != null
        )
        files.add(file)
    }
    return files
}

suspend fun porpfind(url: String, username: String, password: String) {
    val credentials = "$username:$password".encodeToByteArray().encodeBase64()
    val response = HttpClient().request(url) {
        method = HttpMethod("PROPFIND")
        header(HttpHeaders.Authorization, "Basic $credentials")
        header(HttpHeaders.Depth, 1)
    }
    parsePropfindXml(response.bodyAsText())
}
