package kz.assel.techtask.FileApp.repositories;

import kz.assel.techtask.FileApp.models.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Integer> {
}
