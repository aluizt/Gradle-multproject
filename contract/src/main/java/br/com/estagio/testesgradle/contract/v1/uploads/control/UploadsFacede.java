package br.com.estagio.testesgradle.contract.v1.uploads.control;

import br.com.estagio.testesgradle.uploads.services.UploadService;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class UploadsFacede {

    private final UploadService uploadService;

    @Autowired
    public UploadsFacede(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    public void upload(MultipartFile file,String id){
        this.uploadService.upload(file,id);
    }

    public FTPFile[] listFiles(String id){
        return this.uploadService.listarUploads(id);
    }

    public Page<FTPFile> listFailesPagins(String id, Integer page, Integer count){
        return this.uploadService.listarUploadsPaginados(id,page,count);
    }

    public Map<String, FTPFile[]> listSharedFiles(String id){
        return this.uploadService.localizarArquivosCompartilhados(id);
    }

    public ResponseEntity removeFile(String file, String id){
        this.uploadService.removerArquivo(file,id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity download(String file, String id){
        this.uploadService.baixarArquivo(file,id);
        return ResponseEntity.ok().build();
    }
}
