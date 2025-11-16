# Using Room with Kotlin Multiplatform

Room is a database for Android provided by Google that makes it possible to work with SQLite databases and
was introduced as part of Jetpack. While Room was not originally intended for Kotlin Multiplatform (KMP), they have since
added support for KMP making it possible for developers to use Room to embed a SQLite database into their project.

This repository serves as a demo on how to use Room with KMP.

**Important**
To use Room, your codebase must be targeting Android, iOS and JVM. If you do not at least specify these 3 systems, 
you will face errors on the corresponding platform being missing. Additionally, WASM based projects are not supported.
