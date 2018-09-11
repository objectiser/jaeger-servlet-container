/*
 * Copyright (c) 2018, The Jaeger Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package io.jaegertracing.servletcontainer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Tracer;

public class JaegerServletContextListener implements ServletContextListener {

  private JaegerTracer tracer = null;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    tracer = io.jaegertracing.Configuration.fromEnv(sce.getServletContext().getServletContextName())
            .getTracer();
    sce.getServletContext().setAttribute(Tracer.class.getName(), tracer);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    tracer.close();
  }

}