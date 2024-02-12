//: ssia.service.DocumentService.java


package ssia.service;


import ssia.domain.model.Document;
import ssia.repository.DocumentRepository;

import java.util.Optional;


public interface DocumentService {

    Optional<Document> document(String code);

    static DocumentService of(DocumentRepository documentRepository) {
        return new DocumentServiceImpl(documentRepository);
    }
}

///:~