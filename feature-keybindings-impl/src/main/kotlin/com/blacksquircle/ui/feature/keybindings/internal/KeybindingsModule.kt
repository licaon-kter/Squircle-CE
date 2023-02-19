/*
 * Copyright 2023 Squircle CE contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blacksquircle.ui.feature.keybindings.internal

import com.blacksquircle.ui.core.data.storage.keyvalue.SettingsManager
import com.blacksquircle.ui.core.domain.coroutine.DispatcherProvider
import com.blacksquircle.ui.feature.keybindings.data.repository.KeybindingsRepositoryImpl
import com.blacksquircle.ui.feature.keybindings.domain.repository.KeybindingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KeybindingsModule {

    @Provides
    @Singleton
    fun provideKeybindingsRepository(
        dispatcherProvider: DispatcherProvider,
        settingsManager: SettingsManager,
    ): KeybindingsRepository {
        return KeybindingsRepositoryImpl(dispatcherProvider, settingsManager)
    }
}