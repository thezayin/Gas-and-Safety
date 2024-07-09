package com.thezayin.adminhome.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.entities.AdminOptionMenuModel
import kotlinx.coroutines.flow.Flow

interface AdminOptionMenuRepository {
    fun getAdminOptionMenuList(): Flow<Response<List<AdminOptionMenuModel>>>
}
