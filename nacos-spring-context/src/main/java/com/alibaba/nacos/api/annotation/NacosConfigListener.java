/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.nacos.api.annotation;

import com.alibaba.nacos.api.convert.NacosConfigConverter;
import com.alibaba.nacos.client.config.common.Constants;
import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import com.alibaba.nacos.spring.convert.converter.DefaultNacosConfigConverter;

import java.lang.annotation.*;

import static com.alibaba.nacos.client.config.common.Constants.DEFAULT_GROUP;

/**
 * Annotation that marks a method as a listener for Nacos Config change.
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface NacosConfigListener {

    /**
     * Nacos Group ID
     *
     * @return default value {@link Constants#DEFAULT_GROUP};
     */
    String groupId() default DEFAULT_GROUP;

    /**
     * Nacos Data ID
     *
     * @return required value.
     */
    String dataId();

    /**
     * Specify {@link NacosConfigConverter Nacos configuraion convertor} class to convert target type instance.
     *
     * @return If not specified , the {@link DefaultNacosConfigConverter build-in NacosConfigConverter} will be used.
     * @see DefaultNacosConfigConverter
     */
    Class<? extends NacosConfigConverter> converter() default NacosConfigConverter.class;

    /**
     * The {@link NacosProperties} attribute, If not specified, it will use
     * {@link EnableNacos#globalProperties() global Nacos Properties}.
     *
     * @return the default value is {@link NacosProperties}
     * @see EnableNacos#globalProperties()
     */
    NacosProperties properties() default @NacosProperties;

    /**
     * Maximum timeout value of execution in milliseconds, which is used to prevent long-time blocking execution
     * impacting others.
     *
     * @return default value is 1000
     */
    long timeout() default 1000L;

}
