package br.cefet.pparty.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.cefet.pparty.model.FotoUsuario;
import br.cefet.pparty.service.FotoUsuarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/fotoUsuario")
public class FotoUsuarioController 
{

    private final FotoUsuarioService fotoUsuarioService;
    String uploadDirectory = "C:\\Users\\Davi\\Documents\\PParty 6\\App\\Foto" ;

    public FotoUsuarioController(FotoUsuarioService fotoUsuarioService)
    {
        this.fotoUsuarioService = fotoUsuarioService;
    }

    
    @PostMapping(path = "/{idUsuario}/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> saveEmployee(@RequestParam("file") MultipartFile document, @PathVariable("idUsuario") int idUsuario)
    {
        try {

            // Gere um nome de arquivo único com UUID
            // java.util.UUID uuid = java.util.UUID.randomUUID();
            // String fileName = uuid.toString();

            //Nome do arquivo
            String fileName = "teste" + (Math.random()*1000000000);
            String originalFileName = document.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            //String fileExtension;

            //int lastIndex = originalFileName.lastIndexOf(".");
            //     if (lastIndex != -1) 
            //    {
            //        fileExtension = originalFileName.substring(lastIndex);
            //    } 
            //    else 
            //    {
            //        fileExtension = originalFileName;
            // Trate o caso em que não há "." na string originalFileName
            //    }
            // Construa o caminho completo para o arquivo
            java.nio.file.Path filePath = Paths.get(uploadDirectory, fileName);

            // Salve o arquivo no diretório
            File file = new File(uploadDirectory, fileName + fileExtension);
            document.transferTo(file);

            // Salve apenas o caminho no banco de dados...
            String imageUrl = uploadDirectory + "\\" + fileName + fileExtension;
            FotoUsuario foto = new FotoUsuario();
            foto.setUrl(imageUrl); // Salvar o URL no objeto Foto
            foto.setIdUsuario(idUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(fotoUsuarioService.inserir(foto));
        } catch (IOException e) {
            // Tratar exceção de leitura de arquivo
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo.");
        }
    }


    @GetMapping({"/", ""})
    public List<FotoUsuario> consultarTodos(){
        List<FotoUsuario> fotoList = fotoUsuarioService.consultarTodos();

        return fotoList;
    }

    @GetMapping("/{idFoto}")
    public FotoUsuario consultarFoto(@PathVariable("idFoto") int idFoto){
        FotoUsuario ret = fotoUsuarioService.consultarporId(idFoto);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public FotoUsuario inserir (@RequestBody FotoUsuario foto)
    {
        FotoUsuario ret = fotoUsuarioService.inserir(foto);
        return ret;
    }

    @PutMapping({"", "/{idFoto}"})
    public FotoUsuario alterar (@RequestBody FotoUsuario foto)
    {
        FotoUsuario foto2 = fotoUsuarioService.consultarporId(foto.getIdFoto());
        if (foto2 == null)
        {
            throw new RuntimeException( "Nao existe Foto com esse idFoto para ser alterar");
        }
        fotoUsuarioService.alterar(foto);
        return foto;
    }

    @DeleteMapping({"" , "/{idFoto}"})
    public FotoUsuario alterar(@PathVariable ("idFoto") int idFoto)
    {
        FotoUsuario foto = fotoUsuarioService.consultarporId(idFoto);
        if (foto == null)
        {
            throw new RuntimeException( "Nao existe Foto com esse idFoto para ser excluido");
        }
        fotoUsuarioService.delete(idFoto);
        return foto;
    }
}
