package kz.assel.techtask.FileApp.controllers;

import kz.assel.techtask.FileApp.models.FileUpload;
import kz.assel.techtask.FileApp.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/files")
public class MainController {
    private final FileUploadService fileUploadService;

    @Autowired
    public MainController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("files", fileUploadService.getAll());

        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("file", fileUploadService.getById(id));

        return "show";
    }

    @GetMapping("/new")
    public String newFile(@ModelAttribute("file") FileUpload fileUpload){
        return "new";
    }

    @PostMapping()
    public String upload(@RequestParam("file") MultipartFile file, Model model){
        String fileName = file.getOriginalFilename();
        Date fileDate = null;
        String filePath = "/Users/asselzholdasbekova/Downloads/Uploaded files/";;

        try {
            fileDate = new SimpleDateFormat("ddMMyyyy_HHmmss").parse(fileName);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        FileUpload fileUpload = new FileUpload(fileName, fileDate, filePath);
        fileUploadService.add(fileUpload);

        try {
            System.out.println(fileUploadService.upload(file));;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "redirect:/files";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        fileUploadService.delete(id);

        return "redirect:/files";
    }
}