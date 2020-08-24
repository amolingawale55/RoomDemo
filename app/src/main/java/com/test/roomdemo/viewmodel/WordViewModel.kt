package com.test.roomdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.test.roomdemo.db.model.Word
import com.test.roomdemo.db.WordRoomDatabase
import com.test.roomdemo.repo.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    var allWords: LiveData<PagedList<Word>>


    init {
        val wordsDao = WordRoomDatabase.getDatabase(application,viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        val myWordDataSource : DataSource.Factory<Int, Word> =
            wordsDao.getAlphabetizedWords()

        allWords = myWordDataSource.toLiveData(pageSize = 2)

    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}