package com.uicheon.di

import com.uicheon.dao.user.UserDao
import com.uicheon.dao.user.UserDaoImpl
import com.uicheon.repository.user.UserRepository
import com.uicheon.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<UserDao> { UserDaoImpl() }
}