package br.cefet.pparty.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.cefet.pparty.model.FotoFesta;
import br.cefet.pparty.service.FotoFestaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/fotoFesta")
public class FotoFestaController 
{

    private final FotoFestaService fotoService;
    String uploadDirectory = "C:\\Users\\Davi\\Documents\\PParty 6\\App\\Foto" ;

    public FotoFestaController(FotoFestaService fotoservice)
    {
        this.fotoService = fotoservice;
    }

    @PostMapping(path = "/{idFesta}/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> saveEmployee(@RequestParam("file") MultipartFile document, @PathVariable("idFesta") int idFesta)
    {
        try {

            // Gere um nome de arquivo único com UUID
            // java.util.UUID uuid = java.util.UUID.randomUUID();
            // String fileName = uuid.toString();

            //Nome do arquivo
            String fileName = "teste" + (Math.random()*100000000);
            String originalFileName = document.getOriginalFilename();
            // String fileExtension = originalFileName.substring(0, 4);

            // Construa o caminho completo para o arquivo
            java.nio.file.Path filePath = Paths.get(uploadDirectory, fileName);

            // Salve o arquivo no diretório
            File file = new File(uploadDirectory, fileName + originalFileName);
            document.transferTo(file);

            // Salve apenas o caminho no banco de dados...
            String imageUrl = uploadDirectory + "\\" + fileName + originalFileName;
            FotoFesta foto = new FotoFesta();
            foto.setUrl(imageUrl); // Salvar o URL no objeto Foto
            foto.setFkFotoFesta(idFesta);
            return ResponseEntity.status(HttpStatus.OK).body(fotoService.inserir(foto));
        } catch (IOException e) {
            // Tratar exceção de leitura de arquivo
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo.");
        }
    }


    @GetMapping({"/", ""})
    public List<FotoFesta> consultarTodos(){
        List<FotoFesta> fotoList = fotoService.consultarTodos();

        return fotoList;
    }


    @GetMapping("/{fkFotoFesta}/fotofesta")
    public List<FotoFesta> consultarporIdFesta(@PathVariable("fkFotoFesta") int fkFotoFesta){
       List<FotoFesta> fotoList = fotoService.consultarporIdFesta(fkFotoFesta);
        return fotoList;
    }


    @GetMapping("/{idFoto}")
    public FotoFesta consultarFoto(@PathVariable("idFoto") int idFoto){
        FotoFesta ret = fotoService.consultarporId(idFoto);
        return ret;
    }
    


    @PutMapping({"", "/{idFoto}"})
    public FotoFesta alterar (@RequestBody FotoFesta foto)
    {
        FotoFesta foto2 = fotoService.consultarporId(foto.getIdFoto());
        if (foto2 == null)
        {
            throw new RuntimeException( "Nao existe Foto com esse idFoto para ser alterar");
        }
        fotoService.alterar(foto);
        return foto;
    }

    @DeleteMapping({"" , "/{idFoto}"})
    public FotoFesta alterar(@PathVariable ("idFoto") int idFoto)
    {
        FotoFesta foto = fotoService.consultarporId(idFoto);
        if (foto == null)
        {
            throw new RuntimeException( "Nao existe Foto com esse idFoto para ser excluido");
        }
        fotoService.delete(idFoto);
        return foto;
    }
}
