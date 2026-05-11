package com.hyundaiht.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement

class KeepAnnotationDetector : Detector(), Detector.UastScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UClass::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {
            override fun visitClass(node: UClass) {
                // 클래스가 속한 패키지 확인
                val packageName = context.evaluator.getPackage(node.javaPsi)?.qualifiedName ?: return
                
                // ui.data 패키지인지 확인
                if (packageName.contains("ui.data")) {
                    // @Keep 어노테이션이 있는지 확인
                    val hasKeepAnnotation = node.hasAnnotation("androidx.annotation.Keep")
                    
                    if (!hasKeepAnnotation) {
                        context.report(
                            ISSUE,
                            node,
                            context.getNameLocation(node),
                            "ui.data 패키지의 클래스는 난독화 방지를 위해 반드시 @Keep 어노테이션을 가져야 합니다."
                        )
                    }
                }
            }
        }
    }

    companion object {
        val ISSUE = Issue.create(
            id = "MissingKeepAnnotation",
            briefDescription = "@Keep 어노테이션 누락",
            explanation = "ui.data 패키지 내의 모든 클래스는 난독화에서 제외되어야 하므로 @Keep 어노테이션이 필수입니다.",
            category = Category.CORRECTNESS,
            priority = 8,
            severity = Severity.FATAL,
            implementation = Implementation(
                KeepAnnotationDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}
