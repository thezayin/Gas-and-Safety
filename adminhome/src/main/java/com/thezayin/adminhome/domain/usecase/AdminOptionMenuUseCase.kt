package com.thezayin.adminhome.domain.usecase

import com.thezayin.adminhome.domain.repository.AdminOptionMenuRepository
import com.thezayin.entities.AdminOptionMenuModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface AdminOptionMenuUseCase : suspend () -> Flow<Response<List<AdminOptionMenuModel>>>

class AdminOptionMenuUseCaseImpl(private val repository: AdminOptionMenuRepository) :
    AdminOptionMenuUseCase {
    override suspend fun invoke(): Flow<Response<List<AdminOptionMenuModel>>> =
        repository.getAdminOptionMenuList()
}