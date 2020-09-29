package kr.hs.dgsw.juyeop.interview.model.response

class JsonResponse {
    fun returnJsonResponse(status: String, message: String, data: Any): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["status"] = status
        map["message"] = message
        map["data"] = data

        return map
    }
}