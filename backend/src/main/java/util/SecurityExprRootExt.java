package util;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;

public class SecurityExprRootExt extends SecurityExpressionRoot {
    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     */
    public SecurityExprRootExt(Authentication authentication) {
        super(authentication);
    }
}
