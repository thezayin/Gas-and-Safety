package com.thezayin.lpg.presentation.admin.adminHome.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.lpg.presentation.admin.adminHome.domain.model.AdminOptionMenuModel
import com.thezayin.lpg.presentation.admin.adminHome.domain.repository.AdminOptionMenuRepository
import kotlinx.coroutines.flow.Flow

interface AdminOptionMenuUseCase : suspend () -> Flow<Response<List<AdminOptionMenuModel>>>

class AdminOptionMenuUseCaseImpl(private val repository: AdminOptionMenuRepository) :
    AdminOptionMenuUseCase {
    override suspend fun invoke(): Flow<Response<List<AdminOptionMenuModel>>> =
        repository.getAdminOptionMenuList()
}