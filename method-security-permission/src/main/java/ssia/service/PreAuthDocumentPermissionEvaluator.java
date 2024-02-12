//: ssia.service.DataAccessPermissionEvaluator.java


package ssia.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ssia.repository.DocumentRepository;

import java.io.Serializable;


@RequiredArgsConstructor(staticName = "of")
public class PreAuthDocumentPermissionEvaluator implements PermissionEvaluator {

    private final DocumentRepository documentRepository;

    @Override
    public boolean hasPermission(
            Authentication authentication, Object target, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(
            Authentication authentication,
            Serializable targetId,
            String targetType,
            Object permission) {

        var code = targetId.toString();
        var document = this.documentRepository.findDocument(code);
        var permissionExpression = (String) permission;

        boolean admin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(permissionExpression));

        return admin || document.getOwner().equals(authentication.getName());
    }

}///:~