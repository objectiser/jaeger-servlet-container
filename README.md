[![Build Status][ci-img]][ci] [![Coverage Status][cov-img]][cov] [![Released Version][maven-img]][maven] [![FOSSA Status][fossa-img]][fossa]

# Jaeger's Servlet Container Support

This project provides servlet container support for using OpenTracing and Jaeger from within your webapps.

A `ServletContextListener` is used to manage the lifecycle of the Jaeger tracer used within the webapp.
The Jaeger tracer configuration currently is obtained from the [enviroment](https://github.com/jaegertracing/jaeger-client-java/tree/master/jaeger-core#configuration-via-environment), although the service name is
obtained from the servlet context's display name. Therefore by default the tracer will attempt to communicate
with a local Jaeger agent.

The webapp instrumentation is provided by the
[Java web servlet filter](https://github.com/opentracing-contrib/java-web-servlet-filter) instrumentation library.

There are two ways in which your container can make use of OpenTracing and Jaeger:

## Deploy within your webapp

* Add the following dependencies to your webapp:

```
    <dependency>
      <groupId>io.jaegertracing</groupId>
      <artifactId>jaeger-servlet-context</artifactId>
      <version>${jaeger-servlet-context.version}</version>
    </dependency>
    <dependency>
      <groupId>io.opentracing.contrib</groupId>
      <artifactId>opentracing-web-servlet-filter</artifactId>
      <version>${opentracing-web-servlet-filter.version}</version>
    </dependency>
```

* Add the servlet context listener and filter to `web.xml`

```
	<listener>
		<listener-class>io.jaegertracing.servletcontainer.JaegerServletContextListener</listener-class>
	</listener>

	<filter>
		<filter-name>TracingFilter</filter-name>
		<filter-class>io.opentracing.contrib.web.servlet.filter.TracingFilter</filter-class>
	</filter>
```

* Add any appropriate filter mappings to `web.xml`, e.g.

```
	<filter-mapping>
		<filter-name>TracingFilter</filter-name>
		<servlet-name>MyServlet</servlet-name>
		<dispatcher>REQUEST</dispatcher>
	</filter-mapping>
```


## Deploy within your container

### Tomcat

* Unpack the distribution zip/tar in the Tomcat `lib` folder

* Add the servlet context listener and filter entries (shown above) to the `conf/web.xml` file,
under the `web-app` node

* As in the previous section, add a filter mapping(s) to any webapp `web.xml` that needs to be traced

### Jetty

* Unpack the distribution zip/tar in the Tomcat `lib/ext` folder

* Add the servlet context listener and filter entries (shown above) to the `etc/webdefault.xml` file,
under the `web-app` node

* As in the previous section, add a filter mapping(s) to any webapp `web.xml` that needs to be traced


# Contributing and Developing

Please see [CONTRIBUTING.md](CONTRIBUTING.md).


## License
  
[Apache 2.0 License](./LICENSE).


[ci-img]: https://travis-ci.org/jaegertracing/jaeger-client-java.svg?branch=master
[ci]: https://travis-ci.org/jaegertracing/jaeger-client-java
[cov-img]: https://codecov.io/gh/jaegertracing/jaeger-client-java/branch/master/graph/badge.svg
[cov]: https://codecov.io/github/jaegertracing/jaeger-client-java/
[maven-img]: https://img.shields.io/maven-central/v/io.jaegertracing/jaeger-core.svg?maxAge=2000
[maven]: http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.jaegertracing%22
[fossa-img]: https://app.fossa.io/api/projects/git%2Bgithub.com%2Fjaegertracing%2Fjaeger-client-java.svg?type=shield
[fossa]: https://app.fossa.io/projects/git%2Bgithub.com%2Fjaegertracing%2Fjaeger-client-java?ref=badge_shield
[sonatype]: https://oss.sonatype.org/content/repositories/snapshots/io/jaegertracing/
[sonatype-snapshot-instructions]: http://stackoverflow.com/questions/7715321/how-to-download-snapshot-version-from-maven-snapshot-repository
[tracerresolver]: https://github.com/opentracing-contrib/java-tracerresolver
[legacy-client-java]: https://github.com/jaegertracing/legacy-client-java
[javadoc]: http://javadoc.io/doc/io.jaegertracing/jaeger-core
[javadoc-badge]: http://javadoc.io/badge/io.jaegertracing/jaeger-core.svg
