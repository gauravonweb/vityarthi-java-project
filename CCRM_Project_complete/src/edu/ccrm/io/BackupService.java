package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {
    public Path backupFolder(Path sourceFolder, Path baseBackupDir) throws IOException {
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path dest = baseBackupDir.resolve("backup_" + stamp);
        Files.createDirectories(dest);
        // copy files recursively
        Files.walk(sourceFolder).forEach(p -> {
            try {
                Path rel = sourceFolder.relativize(p);
                Path target = dest.resolve(rel);
                if (Files.isDirectory(p)) {
                    Files.createDirectories(target);
                } else {
                    Files.copy(p, target, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) { throw new RuntimeException(e); }
        });
        return dest;
    }

    // recursive size in bytes
    public long recursiveSize(Path p) throws IOException {
        if (!Files.exists(p)) return 0;
        if (Files.isRegularFile(p)) return Files.size(p);
        long sum = 0;
        try (var stream = Files.list(p)) {
            for (Path child : (Iterable<Path>)stream::iterator) {
                sum += recursiveSize(child);
            }
        }
        return sum;
    }
}
