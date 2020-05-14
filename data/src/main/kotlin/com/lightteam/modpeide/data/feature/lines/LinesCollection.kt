/*
 * Licensed to the Light Team Software (Light Team) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The Light Team licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lightteam.modpeide.data.feature.lines

import com.lightteam.modpeide.data.feature.lines.Line

class LinesCollection : Iterable<Line> {

    private val lines = mutableListOf(
        Line(0)
    )

    val lineCount: Int
        get() = lines.size

    fun add(line: Int, index: Int): Line? {
        if (lineCount > 0 && line == 0) {
            return null
        }
        lines.add(line, Line(index))
        return lines[line]
    }

    fun remove(line: Int) {
        if (line != 0) {
            lines.removeAt(line)
        }
    }

    fun clear() {
        lines.clear()
        lines.add(Line(0))
    }

    fun shiftIndexes(fromLine: Int, shiftBy: Int) {
        if (fromLine <= 0) {
            return
        }
        if (fromLine < lineCount) {
            var i = fromLine
            while (i < lineCount) {
                val newIndex = getIndexForLine(i) + shiftBy
                if (i <= 0 || newIndex > 0) {
                    lines[i].start = newIndex
                } else {
                    remove(i)
                    i--
                }
                i++
            }
        }
    }

    fun getIndexForLine(line: Int): Int {
        return if (line >= lineCount) {
            -1
        } else {
            lines[line].start
        }
    }

    fun getLineForIndex(index: Int): Int {
        var first = 0
        var last = lineCount - 1
        while (first < last) {
            val mid = (first + last) / 2
            if (index < getIndexForLine(mid)) {
                last = mid
            } else if (index <= getIndexForLine(mid) || index < getIndexForLine(mid + 1)) {
                return mid
            } else {
                first = mid + 1
            }
        }
        return lineCount - 1
    }

    fun getLine(line: Int): Line {
        if (line > -1 && line < lineCount) {
            return lines[line]
        }
        return Line(0)
    }

    override fun iterator(): Iterator<Line> {
        return lines.iterator()
    }
}