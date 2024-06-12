package com.example.siapp.ViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siapp.Model.ApiResponse
import com.example.siapp.Model.Image
import com.example.siapp.Model.SearchParameters
import com.example.siapp.Repository.CacheRepository
import kotlin.math.log

class ViewModelCache(private val cacheRepository: CacheRepository) : ViewModel() {

    private val _isMatch = MutableLiveData<Boolean>()
    val isMatch: LiveData<Boolean> get() = _isMatch

    fun checkQuery(query: String?):Boolean {
        if (query==null) return false
        val variableString = cacheRepository.getVariableString(query)
        if(variableString != null){
            return true
        }
        else
            return false
    }

    fun saveData(imagess:List<Image>
                 , variableString: String) {
        val list1 = mutableListOf<String>()
        var list2 = mutableListOf<String>()
        var list3 = mutableListOf<String>()
        for (img in imagess){
            list2.add(img.imageUrl)
            list3.add(img.title)
            list1.add(img.link)
        }
        Log.d("TAGcache", "saveData: "+list3.toString())
        Log.d("TAGcache", "saveData: "+list2.toString())
        Log.d("TAGcache", "saveData: "+list1.toString())
        Log.d("TAGcache", "saveData: "+variableString)
        Log.d("TAGcache", "saveData: ===========")
        cacheRepository.saveToCache(list1, list2,list3, variableString)
    }
    fun Take_Apiresponse(s:String):ApiResponse{
        val imgs:List<String> = cacheRepository.getlist_img(s)
        val urls:List<String> = cacheRepository.getlist_url(s)
        val titles:List<String> = cacheRepository.getlist_title(s)

        Log.d("TAGcache1", "saveData: "+titles.toString())
        Log.d("TAGcache1", "saveData: "+imgs.toString())
        Log.d("TAGcache1", "saveData: "+urls.toString())
        Log.d("TAGcache1", "saveData: "+s)
        Log.d("TAGcache1", "saveData: ===========")

        val imageList = mutableListOf<Image>()

        for (index in imgs.indices){
            val image = Image(titles[index], imgs[index], 0, 0,
                "", 0, 0, "", "", urls[index], "", index)
            imageList.add(image)
        }
        val searchPara= SearchParameters("","","",10)
        return ApiResponse(searchPara,imageList)
    }
}