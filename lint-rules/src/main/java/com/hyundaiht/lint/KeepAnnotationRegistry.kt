package com.hyundaiht.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class KeepAnnotationRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(KeepAnnotationDetector.ISSUE)

    override val api: Int
        get() = CURRENT_API
}
