package org.purbarun.integration.model;

public record OrderMessage (OrderRequest orderRequest,String messageId) {
}
