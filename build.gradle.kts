/*
 * Copyright 2019 dev.dorcks Continuum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.nosphere.apache.rat.RatTask

plugins {
    base
    kotlin("jvm") version "1.3.41"
    id("org.nosphere.apache.rat") version "0.6.0"
}

allprojects {
    group = "dev.dorcks.continuum"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        println("Configuring $name in project ${project.name}...")
        kotlinOptions.jvmTarget = "11"
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    subprojects.forEach {
        archives(it)
    }
}

tasks.rat {
    exclude("README.md",
            "**/*.iml", "*.idea/**", "**/out**", // IDEA directories/files
            "gradle/wrapper/**", "gradlew*", "**/build/**", // Gradle directories/files
            ".java-version" // jEnv files
    )
}