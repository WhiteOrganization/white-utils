# White_Utils – Java Utility Library

[![Maven Central](https://img.shields.io/maven-central/v/io.github.white-sdev/white-utils.svg)](https://search.maven.org/artifact/io.github.white-sdev/white-utils)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](#)

A lightweight library containing shared and generic utility classes for Java-based projects.

## 1) What is this repository for?

### 1.1) Quick summary
Version: `1.0.1`

**White_Utils** is a public Java library that provides standardized and generic utility classes to simplify common operations such as logging, formatting, and general helper functions across Java and Maven projects.
It is published for public consumption and is also used as a standard within White Organization.

---

## 2) How to Use

WhiteUtils is designed to be as generic and dependency-minimal as possible so it can be integrated into any Java-based project.

**Add dependency (example):**
```xml
<dependency>
    <groupId>io.github.white-sdev</groupId>
    <artifactId>white-utils</artifactId>
    <version>1.0.1</version>
</dependency>
```

**Primary utilities (examples):**
- `WhiteLogger` — standardized logging helpers (SLF4J-friendly usage).

**Example (placeholder — to be updated when `WhiteLogger` API is finalized):**
```java
@Slf4j
public class MyService implements WhiteLoggeable {
    @Override public org.slf4j.Logger getLogger() { return log;}
    
    public void myProcess(String name, int age) {
        var log = withSignature("myProcess(name, age)");
        log.start("Processing user {} with age {}", name, age);

        log.debug("User age is {}", age);
        if (age < 18) {
            log.warn("User {} is a minor", name);
        }
        log.info("User processed successfully");
        
        log.end("age is now {}", age);
    }
}
```

## 3) Configuration / Setup
How do I get set up for development?

#### 3.1) Development environment
- [JDK](https://openjdk.org/) version: 	`1.8`
- [Maven](https://maven.apache.org/download.cgi)

#### 3.2) Project Dependencies
This library uses:
- **Lombok** to log errors and general logs.
    - **Slf4j**

#### 3.3) Planned Dependencies (in the future)
- **JUnit5**. No tests have been added yet.


### 3.3) Configuration Steps
#### 3.3.1) Environment Configuration
_Please execute the `main-protection-win.bat` file in the root directory of the project
to protect the main branch from being corrupted unintentionally._

You will require all the Development elements in your environment.

An IDE with Maven support is suggested for you to make any modifications to the code.

## 4) How to Deploy?
The deployment process is automated at this point, once the new version is detected to be merge into main from a PR, a GitHub action will build the artifact and upload it to Maven Central.
## 5) What are the Contribution guidelines?

#### 5.1) Writing tests.

- Unit tests using JUnit 5 will be required for new utility code.
- Keep test scope focused and deterministic.

#### 5.2) Code review.

- Create feature branches and open PRs against `main`.
- Provide a clear description of the change, rationale, and any compatibility notes.
- Keep the API surface small and stable. Maintain backward compatibility where feasible.

#### 5.3) Other guidelines.

_Please ask for the code standard to use as a guideline and reflect it in the project._
- Ensure `pom.xml` contains appropriate metadata (groupId, artifactId, version, licenses, SCM, developers) when preparing releases for Maven Central.

## 6) Who do I talk to?

<table>
<thead><tr><th><b>Role</b></th> <th><b>Contact</b></th></tr></thead>
<tr><td>Owner/admin</td><td>current main developer: <a href='mailto:obed.vazquez@gmail.com'>obed.vazquez@gmail.com</a></td></tr>
<tr><td>Supporters</td><td> Currently none</td></tr>
<tr><td>Community</td><td> send us a message in <a href='https://discord.gg/NPY5XcrKE4'>our Discord Server</a></td></tr>
</table>

>Please, contact me if you want to help; In general, I'm developing, maintaining, and supporting this project
on my own with no help or support from anyone; any tip, comment, change, or help in general is well-received.

