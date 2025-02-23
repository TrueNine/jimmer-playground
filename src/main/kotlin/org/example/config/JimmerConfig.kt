package org.example.config


import com.fasterxml.jackson.databind.ObjectMapper
import org.babyfish.jimmer.jackson.ImmutableModule
import org.babyfish.jimmer.sql.kt.cfg.KCustomizer
import org.babyfish.jimmer.sql.kt.cfg.KSqlClientDsl
import org.springframework.stereotype.Component


@Component
class JimmerConfig(
  private val mapper: ObjectMapper
) : KCustomizer {
  init {
    val immModule = ImmutableModule()
    mapper.registerModules(immModule)
  }

  override fun customize(dsl: KSqlClientDsl) {
    dsl.setDefaultSerializedTypeObjectMapper(mapper)
    dsl.setDefaultBinLogObjectMapper(mapper)
  }
}
