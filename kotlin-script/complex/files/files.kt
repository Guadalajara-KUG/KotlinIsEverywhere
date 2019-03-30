import java.io.File
import java.nio.file.Path
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes

fun getOnlyFilesForPath(path: Path): List<File> {
    val mutableFileList = mutableListOf<File>()
    Files.walkFileTree(directory.toPath(), object : FileVisitor<Path> {
        override fun postVisitDirectory(dir: Path?, exc: IOException?) = FileVisitResult.CONTINUE

        override fun visitFileFailed(file: Path?, exc: IOException?) = FileVisitResult.CONTINUE

        override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes?) = FileVisitResult.CONTINUE

        override fun visitFile(file: Path, attrs: BasicFileAttributes?): FileVisitResult {
            mutableFileList.add(file.toFile())
            return FileVisitResult.CONTINUE
        }

    })
    return mutableFileList.toList()
}