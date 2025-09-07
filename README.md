# jofdemo

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/jofdemo-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Flyway ([guide](https://quarkus.io/guides/flyway)): Handle your database schema migrations
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

[Related Hibernate with Panache section...](https://quarkus.io/guides/hibernate-orm-panache)


### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)



# ðŸ“„ Safe Markdown REST API

This project provides a simple REST API that:
- Accepts Markdown input
- Validates and sanitizes it against XSS
- Stores it safely as Markdown
- Returns Markdown or sanitized HTML on demand

---

## 1. `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>markdown-api</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!-- flexmark for markdown parsing/rendering -->
        <dependency>
            <groupId>com.vladsch.flexmark</groupId>
            <artifactId>flexmark-all</artifactId>
            <version>0.64.8</version>
        </dependency>

        <!-- flexmark HTML to Markdown converter -->
        <dependency>
            <groupId>com.vladsch.flexmark</groupId>
            <artifactId>flexmark-html2md-converter</artifactId>
            <version>0.64.8</version>
        </dependency>

        <!-- OWASP Java HTML Sanitizer -->
        <dependency>
            <groupId>com.googlecode.owasp-java-html-sanitizer</groupId>
            <artifactId>owasp-java-html-sanitizer</artifactId>
            <version>20211018.1</version>
        </dependency>
    </dependencies>
</project>
```

---

## 2. `MarkdownApi.java`

```java

                if (body.trim().isEmpty()) {
                    sendResponse(exchange, 400, "{\"error\":\"Markdown cannot be empty\"}");
                    return;
                }

                if (!mdService.looksLikeMarkdown(body)) {
                    sendResponse(exchange, 400, "{\"error\":\"Does not look like valid Markdown\"}");
                    return;
                }

                String safeMarkdown = mdService.sanitizeMarkdown(body);
                String id = UUID.randomUUID().toString();
                db.put(id, safeMarkdown);

                sendResponse(exchange, 200,
                        String.format("{\"id\":\"%s\",\"markdown\":%s}", id, jsonEscape(safeMarkdown)));
                return;
            }

            if ("GET".equalsIgnoreCase(method) && path.startsWith("/notes/")) {
                String[] parts = path.split("/");
                if (parts.length < 3) {
                    sendResponse(exchange, 400, "{\"error\":\"Missing id\"}");
                    return;
                }
                String id = parts[2];
                String markdown = db.get(id);
                if (markdown == null) {
                    sendResponse(exchange, 404, "{\"error\":\"Not found\"}");
                    return;
                }

                if (path.endsWith("/html")) {
                    String safeHtml = mdService.renderToSafeHtml(markdown);
                    sendResponse(exchange, 200,
                            String.format("{\"id\":\"%s\",\"html\":%s}", id, jsonEscape(safeHtml)));
                } else {
                    sendResponse(exchange, 200,
                            String.format("{\"id\":\"%s\",\"markdown\":%s}", id, jsonEscape(markdown)));
                }
                return;
            }

            sendResponse(exchange, 404, "{\"error\":\"Not found\"}");
        }
    }


class MarkdownSecurityService {
    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();
    private final FlexmarkHtmlConverter htmlToMd = FlexmarkHtmlConverter.builder().build();

    private final PolicyFactory sanitizer = Sanitizers.BLOCKS
            .and(Sanitizers.FORMATTING)
            .and(Sanitizers.LINKS)
            .and(Sanitizers.IMAGES)
            .and(Sanitizers.TABLES);

    private static final Pattern MARKDOWN_SYNTAX = Pattern.compile(
            "(?m)(^#{1,6}\\s.+$)|(^[-*+]\\s.+$)|(\\*\\*.+?\\*\\*)|(\\[.+?]\\(.+?\\))"
    );

    public boolean looksLikeMarkdown(String input) {
        return input != null && MARKDOWN_SYNTAX.matcher(input).find();
    }

    public String sanitizeMarkdown(String rawMarkdown) {
        if (rawMarkdown == null) return "";
        Document doc = parser.parse(rawMarkdown);
        String rawHtml = renderer.render(doc);
        String safeHtml = sanitizer.sanitize(rawHtml);
        return htmlToMd.convert(safeHtml);
    }

    public String renderToSafeHtml(String markdown) {
        Document doc = parser.parse(markdown);
        String rawHtml = renderer.render(doc);
        return sanitizer.sanitize(rawHtml);
    }
}
```

---

```