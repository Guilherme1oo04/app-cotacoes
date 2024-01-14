package com.example.appcotaes.utils

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.ByteArrayInputStream
import java.net.ConnectException
import java.net.URI
import javax.xml.parsers.DocumentBuilderFactory

fun coinsList(): List<Map<String, String>> {
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

    val listaMoedas: MutableList<Map<String, String>> = mutableListOf()

    val element = doc.documentElement
    val nodeList = element.childNodes

    for (i in 0 until nodeList.length) {
        val node = nodeList.item(i)
        if (node.nodeType == Node.ELEMENT_NODE) {
            val elem = node as Element
            val sigla = elem.tagName
            val nome = elem.textContent
            listaMoedas.add(
                mapOf(
                    "nome" to nome,
                    "sigla" to sigla
                )
            )
        }
    }

    return listaMoedas.toList()
}