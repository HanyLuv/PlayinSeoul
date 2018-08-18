package com.work.hany.playinseoul.toursearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.toursearch.vo.SearchItem
import com.work.hany.playinseoul.toursearch.vo.SearchSection
import com.work.hany.playinseoul.util.ActivityUtils
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity: DaggerAppCompatActivity() {

    @Inject
    internal lateinit var searchFragment: Lazy<SearchFragment>

    internal var searchItem = SearchItem()
    internal var selectedTagItemList: ArrayList<SelectedTagItem> = ArrayList()

    data class SelectedTagItem (var type: SearchSection.SearchItemType, var area: Area, var view: View)

    interface OnSearchBarSelectedListener {
        fun onSearchBarSelected(position: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchTextView.setOnClickListener {
            Toast.makeText(baseContext, searchItem.toString(),Toast.LENGTH_LONG).show()
        }
        var searchFragment = searchFragment.get()
        ActivityUtils.addFragmentToActivity(supportFragmentManager, searchFragment, R.id.contentFrame)
    }


    private fun deleteTagView(type: SearchSection.SearchItemType, item: Area){
        //selectedTagItemList// 선택된 태그들을 저장한다 타입,뎁스,뷰의 인덱스 의 객체가 있다.
        //현재 삭제 하는 정보보다 상인 뷰들 다 찾아서 제거해야함
        var removeItemList = ArrayList<SelectedTagItem>()
        var it = selectedTagItemList.listIterator()

        while(it.hasNext()){
            var selectedTagItem = it.next()
            if (selectedTagItem.type == type && selectedTagItem.area.depth > item.depth ) {
                searchSelectTagLinearLayout.removeView(selectedTagItem.view)
                removeItemList.add(selectedTagItem)
            }
        }

        removeItemList.forEach {
            selectedTagItemList.remove(it)
        }

    }
    //TODO 사실 얘도 프레젠터 빼서 하는게 맞지만... 행위가 크지않아서 함수로빼서함
    fun updateSelectedTagUi(type: SearchSection.SearchItemType, item: Area) {
        if (type == SearchSection.SearchItemType.EMPTY) return
        searchItem.selectedTag(type, item)

        //하나로 묶자 존나 어려워ㅠㅠ
        var view = LayoutInflater.from(baseContext).inflate(R.layout.search_recycler_row_area_item, searchSelectTagLinearLayout, false)
        view.tag = item.name

        var textView = view.findViewById<TextView>(R.id.tagTitleTextView)
        var params = textView.layoutParams as LinearLayout.LayoutParams
        params.setMargins(0, 2, 5, 2)
        textView.text = item.name
        textView.layoutParams = params

        var tagDeleteTextView = view.findViewById<TextView>(R.id.tagDeleteTextView)
        tagDeleteTextView.visibility = View.VISIBLE
        tagDeleteTextView.setOnClickListener {
            searchSelectTagLinearLayout.post {
                for (i in 0 until searchSelectTagLinearLayout.childCount) {
                    searchSelectTagLinearLayout.getChildAt(i)?.let {
                        if (it.tag == item.name) {
                            searchSelectTagLinearLayout.removeView(it)
                            searchFragment.get().deleteSelectedTag(type, item.depth, searchItem)
                            searchItem.deletedTag(type, item)
                            deleteTagView(type, item)    //하위 뎁스는 다 날려야한다.

                        }
                    }

                }

            }
        }

        /** 동일한 아이템을 클릭했을때 해당아이템 업데이트 해줌. */
        var viewIndex = searchSelectTagLinearLayout.childCount
        var removeItemList = ArrayList<SelectedTagItem>()
        var it = selectedTagItemList.listIterator()

        while (it.hasNext()) {
            var selectedTagItem = it.next()
            if (selectedTagItem.area.depth == item.depth && selectedTagItem.type == type) {
                viewIndex = searchSelectTagLinearLayout.indexOfChild(selectedTagItem.view)
                searchSelectTagLinearLayout.removeView(selectedTagItem.view)
                removeItemList.add(selectedTagItem)

            }
        }

        removeItemList.forEach {
            selectedTagItemList.remove(it)
        }

        selectedTagItemList.add(SelectedTagItem(type, item, view))
        searchSelectTagLinearLayout.addView(view, viewIndex)
    }

    fun nextSelectedAreaTagType(): SearchSection.SearchItemType {
        return if (searchItem.areaCode == "" ||  searchItem.cityCode == "") {
            SearchSection.SearchItemType.AREA
        } else if (searchItem.areaCode != "" && searchItem.cityCode != "") {
            SearchSection.SearchItemType.CITY
        } else {
            SearchSection.SearchItemType.EMPTY
        }
    }

    fun nextSelectedCategryTagType(): SearchSection.SearchItemType  {
        return if (searchItem.contentTypeId == "") {
            SearchSection.SearchItemType.TOUR
        } else if (searchItem.contentTypeId != "" && searchItem.cat1 == "") {
            SearchSection.SearchItemType.TOUR_LARGE
        } else if (searchItem.contentTypeId != "" && searchItem.cat1 != "" && searchItem.cat2 == "") {
            SearchSection.SearchItemType.TOUR_MEDIUM
        } else if (searchItem.contentTypeId != "" && searchItem.cat1 != "" && searchItem.cat2 != "" && searchItem.cat3 == "") {
            SearchSection.SearchItemType.TOUR_SMALL
        } else {
            SearchSection.SearchItemType.EMPTY
        }
    }
}