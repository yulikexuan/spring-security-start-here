//: ssia.repository.DocumentRepository.java


package ssia.repository;


import ssia.domain.model.Document;

import java.util.Map;


public interface DocumentRepository {

    Map<String, Document> DOCUMENTS = Map.of(
            "abc123", Document.of("zac"),
            "qwe123", Document.of("joel"),
            "asd555", Document.of("zac"));

    Document findDocument(String code);

    static DocumentRepository of() {
        return new DocumentRepositoryImpl();
    }
}

class DocumentRepositoryImpl implements DocumentRepository {

    @Override
    public Document findDocument(String code) {
        return DOCUMENTS.get(code);
    }

}///:~