package xyz.gerardbosch.leboncoinfizzbuzz

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode
import io.kotest.extensions.spring.SpringExtension

object KotestConfig : AbstractProjectConfig() {

  override fun extensions() = listOf(SpringExtension)

  override val isolationMode = IsolationMode.InstancePerLeaf

  override suspend fun beforeProject() {}

  override suspend fun afterProject() {}
}
