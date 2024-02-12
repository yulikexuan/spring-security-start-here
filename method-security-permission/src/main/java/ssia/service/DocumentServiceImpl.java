//: ssia.service.DocumentServiceImpl.java


package ssia.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import ssia.domain.model.Document;
import ssia.repository.DocumentRepository;

import java.util.Optional;


@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    // Use PostAuthDocumentPermissionEvaluator for @PostAuthorize
    // @PostAuthorize("hasPermission(returnObject, 'ROLE_ADMIN')")
    // Use PreAuthDocumentPermissionEvaluator for @PreAuthorize
    @PreAuthorize("hasPermission(#code, 'document', 'ROLE_ADMIN')")
    public Optional<Document> document(String code) {
        return Optional.ofNullable(this.documentRepository.findDocument(code));
    }

}///:~