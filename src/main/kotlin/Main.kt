import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.Serializable


fun main() {
    val feedList: List<Feed> = listOf(
        Feed(
            1,
            "1",
            22112021L,
            Content(
                "Turtles are beautiful!",
                listOf(
                    "https://cdnn21.img.ria.ru/images/07e4/02/0d/1564660435_297:0:1440:643_600x0_80_0_0_3f52b46270b92366a609b80577169240.jpg.webp",
                    "https://i.guim.co.uk/img/media/569f6118f54954469ae2bc110e61b6a4f2d3cc82/412_243_2488_1493/master/2488.jpg?width=620&quality=45&auto=format&fit=max&dpr=2&s=90742434a9d4a392ad61ad949cb4f328",
                    "https://imgs.mongabay.com/wp-content/uploads/sites/20/2018/12/21142922/hatchlings_greenturtles3.jpg"
                ),
                listOf("https://www.youtube.com/watch?v=ZS0kXtmXuj8&ab_channel=SEETurtles")
            )
        ),
        Feed(
            2,
            "2",
            22112021L,
            Content(
                "I like this animals!",
                listOf(
                    "https://2day.kh.ua/sites/default/files/styles/glavnoe/public/2020-03/maxresdefault.jpg?h=c673cd1c&itok=NJUeywGn",
                    "https://images.ua.prom.st/204635501_w640_h640_204635501.jpg",
                    "http://cikavo-znaty.com/wp-content/uploads/2017/05/%D0%BF%D1%96%D0%BD%D0%B3%D0%B2%D1%96%D0%BD.jpg"
                ),
                listOf(
                    "https://www.youtube.com/watch?v=BYtA7kJMPy4&ab_channel=Sputnik%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C"
                )
            )
        ),
        Feed(
            3,
            "3",
            22112021L,
            Content(
                "We have a good news! Students Build Solar-Powered Pop-Up Van For Touring in a Brilliant Mobile Home That Has Zero Emissions",
                listOf(
                    "https://www.goodnewsnetwork.org/wp-content/uploads/2021/11/Stella-Vita-with-team-by-Bart-van-Overbeeke-Solar-Team-Eindhoven.jpg",
                    "https://www.goodnewsnetwork.org/wp-content/uploads/2021/11/Stella-Vita-interior-by-Bart-van-Overbeeke-Solar-Team-Eindhoven.jpg",
                    "https://www.goodnewsnetwork.org/wp-content/uploads/2021/11/Stella-Vita-driving-by-Bart-van-Overbeeke-Solar-Team-Eindhoven.jpg"
                ),
                listOf(
                    "https://youtu.be/mJmfmbLRTsw"
                )
            )
        )
    )
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/") {
                call.respondText("Please, use next link: http://0.0.0.0:8080/feed")
            }
            get("/feed") {
                call.respond(feedList)
            }
//            post("/feed") {
//                val customer = call.receive<Customer>()
//                customerStorage.add(customer)
//                call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
//            }

        }
    }.start(wait = true)
}

@Serializable
data class Content(
    val textContent: String,
    val imageUrls: List<String>,
    val videoUrls: List<String>,
)

@Serializable
data class Feed(
    val id: Int,
    val sender_id: String,
    val created: Long,
    val content: Content
)
/*
GET /feed?user="user_id"
// Headers: token=String
[
  {
    id: String,
    sender_id: String,
    created: Date,
    content: {
      text_content: String
      images: [
        String // "http://"
      ]
      video: [
        String // "http://"
      ]
    }
  }
]

POST /feed
//Headers: token=String
{
  id: String,
  sender_id: String,
  created: Date,
  content: {
    text_content: String
    images: [
      String // "http://"
    ]
    video: [
      String // "http://"
    ]
  }
}
*/


