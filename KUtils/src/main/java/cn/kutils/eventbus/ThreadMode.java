/*
 * Copyright (C) 2015 Mr.Simple <bboyfeiyu@gmail.com>
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

package cn.kutils.eventbus;

/**
 * 事件发布的线程模式枚举
 * 
 * @author mrsimple
 */
public enum ThreadMode {
    /**
     * 将事件执行在UI线程
     */
    MAIN,
    /**
     * 在发布线程执行
     */
    POST,
    /**
     * 将事件执行在一个子线程中
     */
    ASYNC
}
