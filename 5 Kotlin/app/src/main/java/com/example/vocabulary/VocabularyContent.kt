package com.example.vocabulary

import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object VocabularyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<WordItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, WordItem> = HashMap()

    private val COUNT = 25

    init {
//        var inputStream: InputStream = File("raw/voiture").inputStream()
        anotherLecture()
//        // Add some sample items.
//        for (i in 1..COUNT) {
//            addItem(createDummyItem(i))
//        }
    }

    private fun addItem(item: WordItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): WordItem {
        return WordItem(
            position.toString(),
            "Item " + position,
            makeDetails(position)
        )
    }

    private fun makeDetails(position: Int): String {
//        val builder = StringBuilder()
//        builder.append("Details about Item: ").append(position)
//        for (i in 0..position - 1) {
//            builder.append("\nMore details information here.")
//        }
//        return builder.toString()
        return ITEMS[position].details
    }

    fun lecture(inputStream: InputStream) {
        var reader= BufferedReader(InputStreamReader(inputStream))
        var line=reader.readLine()
        var count = 0
        while (line!=null){
            val tokens = line.split(";");
            if (tokens.isNotEmpty()){
                addItem(WordItem(""+ count,tokens[0],tokens[1]))
                count++
            }
            line=reader.readLine()
        }
    }

    fun anotherLecture() {
        val myArr = arrayOf(
            "Log book ; carte grise " ,
            "steering wheel ; volant " ,
            "number plate ; numero d'immatriculation " ,
            "seat belt ; ceinture de securite " ,
            "Horn ; klaxon " ,
            "indicator ; clignotant " ,
            "dash board ; tableau de bord " ,
            "head light ; phare " ,
            "rear view mirror ; retroviseur " ,
            "clutch ; ambrayage " ,
            "gear box ; boite de vitesse " ,
            "gear stick ; levier de vitesse " ,
            "head rest ; appui tete " ,
            "hood ; capot " ,
            "oil dip stick ; jauge a huile " ,
            "spare wheel ; roue de secours " ,
            "wheel hub ; enjoliveur " ,
            "windscreen ; parebrise " ,
            "rear windscreen ; lunette arriere " ,
            "jack ; cric " ,
            "warning lights ; feux de detresse " ,
            "windscreen wiper ; essuie glace " ,
            "tyre ; pneu " ,
            "glove box ; boite a gants " ,
            "exhaust pipe ; pot d'echappement " ,
            "shock absorber ; amortisseur " ,
            "driving license ; permis de conduire"
        )
        for (j in 0..26) {
            val line = myArr[j]
            val tokens = line.split(";");
            if (tokens.isNotEmpty()){
                addItem(WordItem(""+ j,tokens[0],tokens[1]))
            }
        }
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class WordItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}
