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

package com.blacksquircle.ui.feature.editor.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.blacksquircle.ui.feature.editor.R
import com.blacksquircle.ui.feature.editor.databinding.DialogGotoLineBinding
import com.blacksquircle.ui.feature.editor.ui.mvi.EditorIntent
import com.blacksquircle.ui.feature.editor.ui.viewmodel.EditorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GotoLineDialog : DialogFragment() {

    private val viewModel by activityViewModels<EditorViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialDialog(requireContext()).show {
            title(R.string.dialog_title_goto_line)
            customView(R.layout.dialog_goto_line)
            negativeButton(android.R.string.cancel)
            positiveButton(R.string.action_go_to) {
                val binding = DialogGotoLineBinding.bind(getCustomView())
                val line = binding.input.text.toString()
                viewModel.obtainEvent(EditorIntent.GotoLineNumber(line))
            }
        }
    }
}