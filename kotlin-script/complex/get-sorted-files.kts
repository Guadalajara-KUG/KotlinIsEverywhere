#!/usr/bin/env kscript

import java.io.File

@file:Include("files/cwd.kt")
val directory: File = cwd()

@file:Include("files/files.kt")
val files = getOnlyFilesForPath(directory.toPath())

println("By directory:")
println(files.sortedBy {
    it.parent
}.joinToString(separator = "\n"))

println("\nBy fileName:")
println(files.sortedBy {
    it.name
}.joinToString(separator = "\n"))