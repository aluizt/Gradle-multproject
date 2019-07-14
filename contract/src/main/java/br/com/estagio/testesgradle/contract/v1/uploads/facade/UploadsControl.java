package br.com.estagio.testesgradle.contract.v1.uploads.facade;

import br.com.estagio.testesgradle.contract.v1.uploads.control.UploadsFacede;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value = "/uploads")
public class UploadsControl {

    private final UploadsFacede uploadsFacede;

    @Autowired
    public UploadsControl(UploadsFacede uploadsFacede) {
        this.uploadsFacede = uploadsFacede;
    }

    @PostMapping(value = "/{id}")
    @ApiOperation(value = "Efetua o upload de um arquivo " )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, o upload foi concluido"),
            @ApiResponse(code = 400, message = "O usuario não foi encontrado"),
            @ApiResponse(code = 401, message = "Você não esta autorizado"),
            @ApiResponse(code = 403, message = "Você não tem acesso a este recurso")
    }
    )
    public ResponseEntity upload(@RequestParam MultipartFile file, @PathVariable(value = "id")String id){
        this.uploadsFacede.upload(file,id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Lista uploads do usuario " )
    @GetMapping(value = "/{id}")
    public ResponseEntity<FTPFile[]> listFiles(@PathVariable(value = "id")String id){
        return new ResponseEntity<>(this.uploadsFacede.listFiles(id),HttpStatus.OK);
    }

    @ApiOperation(value = "Lista uploads do usuario de forma paginada " )
    @GetMapping(value = "/paginado/{id}")
    public ResponseEntity<Page<FTPFile>> listFilesPagins(
            @PathVariable(value = "id") String id,
            @RequestParam("paginas") int page,
            @RequestParam("quantidade") int count) {

        return new ResponseEntity<>(this.uploadsFacede.listFailesPagins(id,page,count),HttpStatus.OK);
    }

    @ApiOperation(value = "Lista arquivos dos amigos que estão compartilhados com o usuario" )
    @GetMapping(value = "/compartilhados/{id}")
    public ResponseEntity<Map<String,FTPFile[]>> listSharedFiles(@PathVariable(value = "id") String id)  {
        return new ResponseEntity<>(this.uploadsFacede.listSharedFiles(id),HttpStatus.OK);
    }

    @ApiOperation(value = "Remove arquivo do usuario " )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity removeFile(
            @PathVariable(value = "id")String id,
            @ApiParam(value = "nome do arquivo")
            @RequestParam(value = "arquivo") String file)  {

        return this.uploadsFacede.removeFile(file,id);
    }

    @ApiOperation(value = "Efetua download de um dos arquivos do usuario")
    @GetMapping(value = "/download/{id}")
    public ResponseEntity download(
            @PathVariable(value = "id")String id,
            @ApiParam(value = "nome do arquivo")
            @RequestParam(value = "arquivo") String file){

      return this.uploadsFacede.download(file,id);
    }
}
