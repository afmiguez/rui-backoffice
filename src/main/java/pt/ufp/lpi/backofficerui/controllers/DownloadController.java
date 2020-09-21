package pt.ufp.lpi.backofficerui.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/files")
public class DownloadController {


    @GetMapping(value = "/{filename}")
    public ResponseEntity<byte[]> getFile(@PathVariable String filename){
        File resource = null;
        try {

            resource = new ClassPathResource(filename+".json").getFile();
            byte[] fileStream= Files.readAllBytes(resource.toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentDispositionFormData(filename+".json", filename+".json");

            return new ResponseEntity<>(fileStream,headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new NoFileException(filename);
        }

    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No file")
    private static class NoFileException extends RuntimeException {
        public NoFileException(String name) {
            super("No such file with name: "+name);
        }
    }
}
