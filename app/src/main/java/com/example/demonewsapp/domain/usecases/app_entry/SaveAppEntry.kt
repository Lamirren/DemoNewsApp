package com.example.demonewsapp.domain.usecases.app_entry

import com.example.demonewsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}