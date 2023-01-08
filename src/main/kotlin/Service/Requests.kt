package Service

object Requests {
    var cookie:String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.76"
    private const val userAgent = ""
    private var body = ""
    fun init(cookie:String){
        this.cookie = cookie
    }
    fun setBody(data:HashMap<String,String>){
        var result = ""
        for ((key,value) in data){
            result = result.plus("$key=$value&")
        }
        result = result.subSequence(0,result.length-1).toString()
        body = result
    }
    fun clearBody(){
        body = ""
    }
    fun post(){

    }
}