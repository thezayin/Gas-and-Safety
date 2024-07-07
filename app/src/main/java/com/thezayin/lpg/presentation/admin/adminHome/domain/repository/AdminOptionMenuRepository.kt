package com.thezayin.lpg.presentation.admin.adminHome.domain.repository

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.adminHome.domain.model.AdminOptionMenuModel
import kotlinx.coroutines.flow.Flow

interface AdminOptionMenuRepository {
    fun getAdminOptionMenuList(): Flow<Response<List<AdminOptionMenuModel>>>
}