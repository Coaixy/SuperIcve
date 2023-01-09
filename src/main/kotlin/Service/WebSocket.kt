package Service

import mjson.Json
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress
import kotlin.Exception

/**
 * WebSocket服务端
 */
class WebSocket(port: Int) : WebSocketServer(InetSocketAddress(port)) {
    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        Logger.info("连接建立")
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        Logger.info("服务端已关闭 127.0.0.1:$port")
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        val msg = message.toString()
        if (msg != ""){
            try {
                val jsonData = Json.read(msg)
                when(jsonData.at("type").asString()){
                    "cookie" -> {
                        Requests.cookie = jsonData.at("data").asString()
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        Logger.error("连接出错")
    }

    override fun onStart() {
        Logger.info("服务端已启动:127.0.0.1:$port")
    }

}