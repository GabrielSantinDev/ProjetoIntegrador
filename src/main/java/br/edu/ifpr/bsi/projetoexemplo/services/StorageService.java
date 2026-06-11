package br.edu.ifpr.bsi.projetoexemplo.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StorageService {

    @Autowired
    private Cloudinary cloudinary;

    public record UploadResponse(String url, String publicId) {}

    public UploadResponse upload(String pasta, MultipartFile arquivo, String idPublico) {
        try {
            var uploadResult = cloudinary.uploader().upload(arquivo.getBytes(),
                    ObjectUtils.asMap(
                            "folder", pasta,
                            "public_id", idPublico,
                            "overwrite", true,
                            "resource_type", "image"
                    ));

            String url = uploadResult.get("secure_url").toString();
            String publicId = uploadResult.get("public_id").toString();

            return new UploadResponse(url, publicId);

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Falha no upload do arquivo. " + e.getMessage()
            );
        }
    }

    public void delete(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId,
                    ObjectUtils.asMap("resource_type", "image")
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao remover imagem: " + e.getMessage()
            );
        }
    }
}