package com.microservicio.cards.services.dtos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Frank
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {
    
    private String cardNumber;
    private boolean active;
}
