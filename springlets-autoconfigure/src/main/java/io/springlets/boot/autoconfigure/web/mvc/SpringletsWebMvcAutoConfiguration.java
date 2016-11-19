/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.springlets.boot.autoconfigure.web.mvc;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.springlets.web.mvc.config.EnableSpringletsWebMvcAdvices;
import io.springlets.web.mvc.config.SpringletsWebMvcConfiguration;
import io.springlets.web.mvc.config.SpringletsWebMvcProperties;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Springlets Web MVC
 * integration.
 * 
 * Activates when the application is a web application and no
 * {@link SpringletsWebMvcConfiguration} is found.
 * 
 * Once in effect, the auto-configuration allows to configure any property of
 * {@link SpringletsWebMvcConfiguration} using the `springlets.mvc` prefix 
 * 
 * @author Enrique Ruiz at http://www.disid.com[DISID Corporation S.L.]
 */
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@ConditionalOnClass(SpringletsWebMvcConfiguration.class)
@ConditionalOnMissingBean(SpringletsWebMvcConfiguration.class)
@ConditionalOnWebApplication
@Configuration
@EnableConfigurationProperties
@EnableSpringletsWebMvcAdvices
public class SpringletsWebMvcAutoConfiguration {

  @Bean
  @ConditionalOnProperty(prefix = "springlets.mvc.advices", name = "enabled", matchIfMissing = true)
  @ConfigurationProperties(prefix = "springlets.mvc")
  public SpringletsWebMvcProperties springletsWebMvcProperties() {
      return new SpringletsWebMvcProperties();
  }
}
