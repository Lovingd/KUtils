/*
 *  Copyright (C) 2017 Bilibili
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package cn.kutils.boxing.utils;

import android.util.Log;

import cn.kutils.boxing.model.BoxingBuilderConfig;


/**
 * Debug log tool.
 *
 * @author ChenSL
 */
public class BoxingLog {
    private static final String TAG = "cn.kutils.boxing";

    public static void d(String log) {
        if (BoxingBuilderConfig.DEBUG && !BoxingBuilderConfig.TESTING) {
            Log.d(TAG, log);
        }
    }
}
