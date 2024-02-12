//: ssia.web.controller.DocumentController.java


package ssia.web.controller;


import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ssia.domain.model.Document;
import ssia.service.DocumentService;


@RestController
@AllArgsConstructor
class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/documents/{code}")
    Document documentDetails(@PathVariable("code") String code) {
        return this.documentService.document(code).orElseThrow(
                IllegalArgumentException::new);
    }

}///:~