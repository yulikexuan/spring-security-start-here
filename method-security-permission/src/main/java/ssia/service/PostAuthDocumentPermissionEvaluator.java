//: ssia.service.DocumentPermissionEvaluator.java


package ssia.service;


import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ssia.domain.model.Document;

import java.io.Serializable;
import java.util.Optional;


public class PostAuthDocumentPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(
            Authentication authentication,
            Object targetDomainObject,
            Object permission) {

        var permissionExpression = (String) permission;

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(permissionExpression));

        return isAdmin || ((Optional<Document>) targetDomainObject)
                .map(Document::getOwner)
                .map(owner -> owner.equals(authentication.getName()))
                .orElse(false);
    }

    @Override
    public boolean hasPermission(
            Authentication authentication,
            Serializable targetId,
            String targetType,
            Object permission) {
        return false;
    }

}///:~