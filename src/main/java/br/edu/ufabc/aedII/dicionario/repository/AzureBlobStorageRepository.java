package br.edu.ufabc.aedII.dicionario.repository;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobRange;
import com.azure.storage.blob.models.BlobStorageException;
import com.azure.storage.blob.options.BlobInputStreamOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.azure.storage.blob.BlobContainerClient;

import java.io.InputStream;

@Service
public class AzureBlobStorageRepository {

    @Autowired
    BlobServiceClient blobServiceClient;
    public long getTamanhoArquivo(String container, String nomeArquivo) {
        return this.blobServiceClient
                .getBlobContainerClient(container)
                .getBlobClient(nomeArquivo)
                .getProperties()
                .getBlobSize();
    }
    public InputStream baixarArquivoEmChunks(String container, String nomeArquivo, long offset, long length) {
        try{
            BlobRange blobRange = new BlobRange(offset, offset + length);

            return this.blobServiceClient
                    .getBlobContainerClient(container)
                    .getBlobClient(nomeArquivo)
                    .openInputStream(new BlobInputStreamOptions().setRange(blobRange));
        }
        catch (BlobStorageException ex) {
            System.err.println("Azure Storage error: " + ex.getMessage());
            System.err.println("Error details: " + ex.getValue());
        }
        return null;
    }
}

