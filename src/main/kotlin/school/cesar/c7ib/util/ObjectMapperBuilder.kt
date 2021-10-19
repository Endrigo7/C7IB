package school.cesar.c7ib.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object ObjectMapperBuilder {


    fun build(): ObjectMapper =
        jacksonObjectMapper().apply {
            propertyNamingStrategy = PropertyNamingStrategy.KEBAB_CASE

            registerModule(SimpleModule())
            registerModule(JavaTimeModule())
            
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        }


}
