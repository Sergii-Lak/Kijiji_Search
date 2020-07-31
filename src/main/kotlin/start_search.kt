import org.jsoup.Jsoup


interface Strings_information {
    var base_url: String
    var ending_url: String
    var page_numero: String
    var search_word: String
    val page_numbers: Int

    fun CreateWords(): String {
        val word = search_word.trim()
        return word.replace(' ', '-')
    }

    fun UrlCreator(): List<String> {
        val L = mutableListOf<String>()
        for (j in (1..page_numbers)) {
            L.add("$base_url${CreateWords()}$page_numero$j$ending_url")
        }
        return L
    }
}

abstract class First_class(SearchWord: String, PageNumbers: Int) : Strings_information {

    override var search_word: String = SearchWord
    override val page_numbers: Int = PageNumbers
    override var page_numero: String = "/page-"

    private val DivPrimary = "div[class=info-container]"
    private val Title_cssQuery = "div[class = title]"
    private val Price_cssQuery = "div[class = price]"
    private val Location_cssQuery = "div[class = location] span[class = ]"
    private val Date_Houres_cssQuery = "div[class = location] span[class = date-posted]"
    private val Description_cssQuery = "div[class = description]"
    private val URL_cssQuery = "div[class=title] a[href]"

    fun SearchInTitle() {
        var count = 0
        for (i in UrlCreator()) {
            val Doc_open = Jsoup.connect(i).get()
            val Primary_ = Doc_open.select(DivPrimary)
            val QuantityElements = Primary_.count()


            for (el in (0 until QuantityElements)) {
                val Title_ = Primary_[el].select(Title_cssQuery)
                if (Title_.text().toLowerCase().contains(search_word.toLowerCase())) {
                    val Price_ = Primary_[el].select(Price_cssQuery)
                    val Locations_ = Primary_[el].select(Location_cssQuery)
                    val Date_ = Primary_[el].select(Date_Houres_cssQuery)
                    val Description_ = Primary_[el].select(Description_cssQuery)
                    val URL_ = Primary_[el].select(URL_cssQuery)


                    println(Title_.text())
                    println(Price_.text())
                    try {
                        println(Locations_.text())
                    } catch (e: IndexOutOfBoundsException) {
                        println("NO location in url!")
                    }
                    try {
                        println(Date_.text())
                    } catch (e: IndexOutOfBoundsException) {
                        println("NO date and hour in url!")
                    }
                    try {
                        println(Description_.text())
                    } catch (e: IndexOutOfBoundsException) {
                        println("NO description in url!")
                    }
                    println("https://www.kijiji.ca${URL_.attr("href")}")
                    println("")
                    println("--------------------------------")
                    println("")

                    count++
                }

            }
        }
        println("Total offers quantity found: $count")
    }

}

class SearchBuySell(SearchWord: String, PageNumbers: Int) : First_class(SearchWord, PageNumbers) {

    override var base_url: String = "https://www.kijiji.ca/b-achat-et-vente/grand-montreal/"
    override var ending_url: String = "/k0c10l80002"
}

class SearchFreeStuff(SearchWord: String, PageNumbers: Int) : First_class(SearchWord, PageNumbers) {

    override var base_url: String = "https://www.kijiji.ca/b-objets-gratuits/grand-montreal/"
    override var ending_url: String = "/c17220001l80002"
}

class SearchCarsTrucks(SearchWord: String, PageNumbers: Int) : First_class(SearchWord, PageNumbers) {

    override var base_url: String = "https://www.kijiji.ca/b-autos-camions/grand-montreal/"
    override var ending_url: String = "/c174l80002a49"
}

class SearchRealEstate(SearchWord: String, PageNumbers: Int) : First_class(SearchWord, PageNumbers) {
    override var base_url: String = "https://www.kijiji.ca/b-immobilier/grand-montreal/"
    override var ending_url: String = "/c34l80002"
}


fun main() {

    val a = SearchRealEstate("Griffintown", 3)
    a.SearchInTitle()

}