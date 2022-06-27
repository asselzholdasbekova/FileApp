package kz.assel.techtask.FileApp.services;

import kz.assel.techtask.FileApp.models.FileUpload;
import kz.assel.techtask.FileApp.repositories.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class FileUploadService {

    private final FileUploadRepository fileUploadRepository;
    private final FileUpload fileUpload = new FileUpload();

    @Autowired
    public FileUploadService(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    @Transactional(readOnly = true)
    public List<FileUpload> getAll(){
        return fileUploadRepository.findAll();
    }

    @Transactional(readOnly = true)
    public FileUpload getById(int id){
        Optional<FileUpload> fileUpload = fileUploadRepository.findById(id);

        return fileUpload.orElse(null);
    }

    @Transactional
    public void add(FileUpload fileUpload){
        fileUploadRepository.save(fileUpload);
    }

    @Transactional
    public void delete(int id){
        fileUploadRepository.deleteById(id);
    }

    public ResponseEntity<?> upload(MultipartFile file) throws ParseException {
        String fileName = file.getOriginalFilename();
        String filePath = "/Users/asselzholdasbekova/Downloads/Uploaded files/";

        try {
            file.transferTo(new File(filePath + fileName));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok("File uploaded successfully!");
    }

}
