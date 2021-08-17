package com.rereverb.order.config;

import com.fasterxml.jackson.datatype.threetenbp.ThreeTenModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZonedDateTime;

@Configuration
public class JacksonConfiguration {

  @Bean
  @ConditionalOnMissingBean(ThreeTenModule.class)
  ThreeTenModule threeTenModule() {
    ThreeTenModule module = new ThreeTenModule();
    module.addDeserializer(Instant.class, com.rereverb.order.config.CustomInstantDeserializer.INSTANT);
    module.addDeserializer(OffsetDateTime.class, com.rereverb.order.config.CustomInstantDeserializer.OFFSET_DATE_TIME);
    module.addDeserializer(ZonedDateTime.class, com.rereverb.order.config.CustomInstantDeserializer.ZONED_DATE_TIME);
    return module;
  }
}
