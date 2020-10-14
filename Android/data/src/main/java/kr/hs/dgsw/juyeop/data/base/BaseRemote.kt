package kr.hs.dgsw.juyeop.data.base

import kr.hs.dgsw.juyeop.data.util.Response
import io.reactivex.functions.Function
import org.json.JSONObject

abstract class BaseRemote<SV> {

    abstract val service: SV

    protected fun <T> getResponse(): Function<retrofit2.Response<Response<T>>, T> {
        return Function { response: retrofit2.Response<Response<T>> ->
            checkError(response)
            response.body()!!.data
        }
    }

    private fun checkError(response: retrofit2.Response<*>) {
        if (!response.isSuccessful) {
            val errorBody = JSONObject(response.errorBody().toString())
            throw Throwable(errorBody.getString("message"))
        }
    }
}