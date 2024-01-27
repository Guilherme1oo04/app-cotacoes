package com.example.appcotaes.utils

import com.example.appcotaes.data.CoinModel
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.ByteArrayInputStream
import java.net.ConnectException
import java.net.URI
import javax.xml.parsers.DocumentBuilderFactory

class CoinsList {
    private val items = mutableListOf<CoinModel>()

    fun loadCoins(): List<CoinModel> {
        val response = try {
            URI("https://economia.awesomeapi.com.br/xml/available/uniq").toURL()
                .openStream()
                .bufferedReader()
                .use {it.readText()}
        } catch(e: ConnectException) {
            e.printStackTrace()
        }

        val dbFactory = DocumentBuilderFactory.newInstance()
        val dBuilder = dbFactory.newDocumentBuilder()
        val xmlInput = ByteArrayInputStream(response.toString().trimIndent().toByteArray(Charsets.UTF_8))
        val doc = dBuilder.parse(xmlInput)

        val element = doc.documentElement
        val nodeList = element.childNodes

        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            if (node.nodeType == Node.ELEMENT_NODE) {
                val elem = node as Element
                val sigla = elem.tagName
                val nome = elem.textContent
                items.add(
                    CoinModel(sigla = sigla, nome = nome)
                )
            }
        }

        return items.toList()
    }
}