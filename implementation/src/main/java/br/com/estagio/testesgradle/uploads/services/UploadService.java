package br.com.estagio.testesgradle.uploads.services;

import br.com.estagio.testesgradle.friends.model.Friends;
import br.com.estagio.testesgradle.friends.service.FriendsService;
import br.com.estagio.testesgradle.ftp.exceptions.ErrorFtpException;
import br.com.estagio.testesgradle.ftp.services.FtpService;
import br.com.estagio.testesgradle.ftp.Conect.ConectServer;
import br.com.estagio.testesgradle.user.model.User;
import br.com.estagio.testesgradle.user.services.UserService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UploadService {


    private UserService usuarioService;
    private FriendsService amigoService;
    private ConectServer conectServer;
    private FtpService ftpService;

    @Autowired
    public UploadService(UserService userService,
                         FriendsService friendsService,
                         ConectServer conectServer,
                         FtpService ftpService) {

        this.usuarioService = userService;
        this.amigoService = friendsService;
        this.conectServer = conectServer;
        this.ftpService = ftpService;
    }

    public void upload(MultipartFile arquivo, String id){

        User usuario = this.usuarioService.consultarId(id);
        FTPClient ftpClient = getConexao(usuario);

        try {

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            try {
                ftpClient.storeFile(arquivo.getOriginalFilename(),arquivo.getInputStream());
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally {
                ftpClient.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FTPFile[] listarUploads(String id){

        User usuario = this.usuarioService.consultarId(id);

        FTPClient ftpClient = getConexao(usuario);

        try {

            FTPFile[] files = ftpClient.listFiles();

            if(files.length==0){
                return null;
            }
            return files;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public Page<FTPFile> listarUploadsPaginados(String id, int page, int count){

        User usuario = this.usuarioService.consultarId(id);

        FTPClient ftpClient = getConexao(usuario);

        try {

            return getPaginacao(ftpClient.listFiles(),page,count);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public void removerArquivo(String nomeArquivo, String id){

        User usuario = this.usuarioService.consultarId(id);
        FTPClient ftpClient = getConexao(usuario);

        try {
            if(!ftpClient.deleteFile(nomeArquivo)){
                throw new ErrorFtpException("O arquivo informado não foi encontrado !!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void baixarArquivo(String nomeArquivo, String id){
        User usuario = this.usuarioService.consultarId(id);
        FTPClient ftpClient = getConexao(usuario);


        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream("/home/alexandre/Downloads/"+nomeArquivo);


            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(nomeArquivo,fileOutputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public User localizarId(String id) {

        return this.usuarioService.consultarId(id);
    }

    private Page<FTPFile> getPaginacao(FTPFile[] files, int page, int count){

        PageRequest pageRequest = new PageRequest(page,count);

        List<FTPFile> lista = new ArrayList<>();
        for(FTPFile f:files){
            lista.add(f);
        }

        lista.forEach(f -> System.out.println(f.getName()));

        int max = (count*(page+1)>lista.size())? lista.size():count*(page+1);

        Page<FTPFile> ftpFilePage =
                new PageImpl<FTPFile>(lista.subList(page*count,max),
                                           pageRequest,lista.size());

        return  ftpFilePage;

    }


    private FTPClient getConexao(User usuario){

        FTPClient ftpClient = this.conectServer.conectarServer();
        ftpClient.enterLocalPassiveMode();
        ftpClient=this.ftpService.verificaDiretorio(usuario.getIdUsuario(),ftpClient);

        return ftpClient;
    }



    public Map<String, FTPFile[]> localizarArquivosCompartilhados(String id)  {

        List<Friends> listaAmigos = this.amigoService.listAllFrinds(id);

        if(listaAmigos == null){
            throw new ErrorFtpException("Usuario não possui amigos cadastrados !!");
        }

        Map<String, FTPFile[]> mapa = new HashMap<>();

        FTPFile[] arquivosFTP;

        for (Friends a:listaAmigos){

            arquivosFTP=this.listarUploads(a.getIdAmigo());

            if(arquivosFTP != null ){

                mapa.put(a.getIdAmigo(),arquivosFTP);
            }

        }

        return mapa;
    }
}
