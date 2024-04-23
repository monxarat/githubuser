/*
 * Copyright (C) 2024 The Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.clcosmos.githubuser.view

/**
 * SpinnerItem is a data class that represents an item in a spinner.
 *
 * @property text The text to be displayed for the item in the spinner.
 * @property iconId The resource ID of the icon to be displayed for the item in the spinner.
 */
data class SpinnerItem(val text: String, val iconId: Int)